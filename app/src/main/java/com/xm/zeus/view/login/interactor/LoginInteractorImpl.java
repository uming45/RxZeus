package com.xm.zeus.view.login.interactor;

import android.content.Context;
import android.text.TextUtils;

import com.xm.zeus.app.Constant;
import com.xm.zeus.chat.Xmpp;
import com.xm.zeus.db.app.entity.Colleague;
import com.xm.zeus.db.app.entity.Friend;
import com.xm.zeus.db.app.entity.Group;
import com.xm.zeus.db.app.entity.Org;
import com.xm.zeus.db.app.entity.User;
import com.xm.zeus.db.app.helper.ColleagueHelper;
import com.xm.zeus.db.app.helper.FriendHelper;
import com.xm.zeus.db.app.helper.GroupHelper;
import com.xm.zeus.db.app.helper.OrgHelper;
import com.xm.zeus.db.app.helper.UserHelper;
import com.xm.zeus.network.Network;
import com.xm.zeus.network.extend.ApiSubscriber;
import com.xm.zeus.utils.PinYin;
import com.xm.zeus.utils.Utils;
import com.xm.zeus.view.login.entity.LoginResult;

import org.jivesoftware.smack.AbstractXMPPConnection;

import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lvxia on 2016-04-01.
 */
public class LoginInteractorImpl implements ILoginInteractor {

    private UserHelper userHelper;
    private ColleagueHelper personHelper;
    private FriendHelper friendHelper;
    private OrgHelper orgHelper;
    private GroupHelper groupHelper;

    private PinYin pinYin;

    public LoginInteractorImpl() {
        pinYin = new PinYin();
        userHelper = new UserHelper();
        personHelper = new ColleagueHelper();
        friendHelper = new FriendHelper();
        orgHelper = new OrgHelper();
        groupHelper = new GroupHelper();
    }

    @Override
    public boolean checkUserName(String username) {
        return TextUtils.isEmpty(username);
    }

    @Override
    public boolean checkPassword(String psw) {
        return TextUtils.isEmpty(psw);
    }

    @Override
    public boolean checkNet(Context ctx) {
        return Network.isAvailable(ctx);
    }

    @Override
    public void loginToBusiness(String username, String password, String org, String appKey, ApiSubscriber<User> callback) {

        final User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setOrg(org);

        String mdPsw = Utils.getMd5(password).toLowerCase(Locale.getDefault());

        Network.getZeusApis().login(username, mdPsw, org, appKey)
                .compose(Network.<LoginResult>check())
                .subscribeOn(Schedulers.io())
                .map(new Func1<LoginResult, User>() {
                    @Override
                    public User call(LoginResult loginResult) {
                        user.setUserId(loginResult.getUid());
                        user.setToken(loginResult.getAccessToken());
                        user.setLogged(true);
                        user.setAutoLogin(true);
                        saveLoggedUser(user);
                        return user;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);

    }

    private void saveLoggedUser(User user) {
        userHelper.saveUser(user);
    }

    @Override
    public void loginToXmpp(String username, String password, final LoginListener listener) {
        Xmpp.getInstance().login(Constant.ServiceName, Constant.ServiceHost, Constant.ServicePort,
                username, password, Constant.PlatformResource, new Subscriber<AbstractXMPPConnection>() {
                    @Override
                    public void onCompleted() {
                        listener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(e);
                    }

                    @Override
                    public void onNext(AbstractXMPPConnection connection) {
                        Xmpp.getInstance().setXmppConnection(connection);
                    }
                });
    }

    @Override
    public void downloadContacts(final User user, final long colleagueTS, final long friendTS, ApiSubscriber subscriber) {

        Network.getZeusApis().getColleague(user.getToken(), user.getUserId(), Constant.Organization, colleagueTS, "false")
                .subscribeOn(Schedulers.io())
                .compose(Network.<List<Colleague>>check())
                .flatMap(new Func1<List<Colleague>, Observable<List<Friend>>>() {
                    @Override
                    public Observable<List<Friend>> call(List<Colleague> colleagueList) {
                        processColleague(colleagueList);
                        return Network.getZeusApis().getFriends(user.getToken(), user.getUserId(), Constant.Organization, friendTS, "false")
                                .subscribeOn(Schedulers.io())
                                .compose(Network.<List<Friend>>check());
                    }
                })
                .flatMap(new Func1<List<Friend>, Observable<Org>>() {
                    @Override
                    public Observable<Org> call(List<Friend> friends) {
                        processFriend(friends);
                        return Network.getZeusApis().getOrgs(user.getToken(), user.getUserId(), Constant.Organization)
                                .subscribeOn(Schedulers.io())
                                .compose(Network.<Org>check());
                    }
                })
                .flatMap(new Func1<Org, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(Org org) {
                        processOrg(org);
                        return Network.getZeusApis().getGroup(user.getToken(), user.getUserId(), Constant.Organization)
                                .subscribeOn(Schedulers.io())
                                .compose(Network.<List<Group>>check())
                                .map(new Func1<List<Group>, Boolean>() {
                                    @Override
                                    public Boolean call(List<Group> groups) {
                                        return processGroup(groups);
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    private boolean processColleague(List<Colleague> colleagues) {

        try {

            if (colleagues == null) {
                return true;
            }

            for (Colleague colleague : colleagues) {

                if (colleague.getType() == 1) {// 删除

                    personHelper.deleteById(colleague.getUid());

                } else { // 新增或修改

                    String selling = pinYin.toPinYin(colleague.getUsername());
                    String firstLetter = selling.substring(0, 1).toUpperCase(Locale.getDefault());
                    colleague.setSpelling(selling);
                    colleague.setFirstletter(firstLetter);
                    colleague.setTimestamp(System.currentTimeMillis());

                    personHelper.saveOrUpdate(colleague);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean processFriend(List<Friend> friends) {

        try {

            if (friends == null) {
                return true;
            }

            for (Friend friend : friends) {

                if (friend.getType() == 1) {
                    friendHelper.deleteById(friend.getUid());
                } else {
                    String selling = pinYin.toPinYin(friend.getUsername());
                    String firstLetter = selling.substring(0, 1).toUpperCase(Locale.getDefault());
                    friend.setSpelling(selling);
                    friend.setFirstLetter(firstLetter);
                    friend.setTimestamp(System.currentTimeMillis());
                    friendHelper.saveOrUpdate(friend);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean processOrg(Org org) {
        try {
            if (org == null) {
                return true;
            }

            if (TextUtils.isEmpty(org.getPid())) {
                org.setPid(Org.TopLevel);
            }

            orgHelper.add(org);
            if (org.getChildren() != null && org.getChildren().size() > 0) {
                for (Org child : org.getChildren())
                    processOrg(child);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean processGroup(List<Group> groups) {
        try {

            if (groups == null) {
                return true;
            }

            for (Group group : groups) {
                group.setDataType(Group.DATATYPE_GROUP);
                group.setHeadName("讨论组");
                group.setTimestamp(System.currentTimeMillis());
            }
            if (groups.size() > 0) {
                groupHelper.addGroups(groups);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
