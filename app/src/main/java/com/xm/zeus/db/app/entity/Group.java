package com.xm.zeus.db.app.entity;

import java.util.List;
import com.xm.zeus.db.app.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.xm.zeus.db.app.dao.GroupDao;
import com.xm.zeus.db.app.dao.GroupMemberIdDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "Group".
 */
public class Group {

    private String uid;
    private String name;
    private String avatarid;
    private String headName;
    private Integer dataType;
    private Long timestamp;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient GroupDao myDao;

    private List<GroupMemberId> memberIds;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Group() {
    }

    public Group(String uid) {
        this.uid = uid;
    }

    public Group(String uid, String name, String avatarid, String headName, Integer dataType, Long timestamp) {
        this.uid = uid;
        this.name = name;
        this.avatarid = avatarid;
        this.headName = headName;
        this.dataType = dataType;
        this.timestamp = timestamp;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGroupDao() : null;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarid() {
        return avatarid;
    }

    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<GroupMemberId> getMemberIds() {
        if (memberIds == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GroupMemberIdDao targetDao = daoSession.getGroupMemberIdDao();
            List<GroupMemberId> memberIdsNew = targetDao._queryGroup_MemberIds(uid);
            synchronized (this) {
                if(memberIds == null) {
                    memberIds = memberIdsNew;
                }
            }
        }
        return memberIds;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetMemberIds() {
        memberIds = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
