package com.xm.zeus.db.app.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.xm.zeus.db.app.entity.Colleague;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Colleague".
*/
public class ColleagueDao extends AbstractDao<Colleague, String> {

    public static final String TABLENAME = "Colleague";

    /**
     * Properties of entity Colleague.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Uid = new Property(0, String.class, "uid", true, "UID");
        public final static Property Uno = new Property(1, String.class, "uno", false, "UNO");
        public final static Property Username = new Property(2, String.class, "username", false, "USERNAME");
        public final static Property Sex = new Property(3, String.class, "sex", false, "SEX");
        public final static Property Mobile = new Property(4, String.class, "mobile", false, "MOBILE");
        public final static Property Email = new Property(5, String.class, "email", false, "EMAIL");
        public final static Property Avatarid = new Property(6, String.class, "avatarid", false, "AVATARID");
        public final static Property Type = new Property(7, Integer.class, "type", false, "TYPE");
        public final static Property Spelling = new Property(8, String.class, "spelling", false, "SPELLING");
        public final static Property Firstletter = new Property(9, String.class, "firstletter", false, "FIRSTLETTER");
        public final static Property DataType = new Property(10, Integer.class, "dataType", false, "DATA_TYPE");
        public final static Property HeadName = new Property(11, String.class, "headName", false, "HEAD_NAME");
        public final static Property IsCheck = new Property(12, Boolean.class, "isCheck", false, "IS_CHECK");
        public final static Property Timestamp = new Property(13, Long.class, "timestamp", false, "TIMESTAMP");
    };

    private DaoSession daoSession;


    public ColleagueDao(DaoConfig config) {
        super(config);
    }
    
    public ColleagueDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Colleague\" (" + //
                "\"UID\" TEXT PRIMARY KEY NOT NULL ," + // 0: uid
                "\"UNO\" TEXT," + // 1: uno
                "\"USERNAME\" TEXT," + // 2: username
                "\"SEX\" TEXT," + // 3: sex
                "\"MOBILE\" TEXT," + // 4: mobile
                "\"EMAIL\" TEXT," + // 5: email
                "\"AVATARID\" TEXT," + // 6: avatarid
                "\"TYPE\" INTEGER," + // 7: type
                "\"SPELLING\" TEXT," + // 8: spelling
                "\"FIRSTLETTER\" TEXT," + // 9: firstletter
                "\"DATA_TYPE\" INTEGER," + // 10: dataType
                "\"HEAD_NAME\" TEXT," + // 11: headName
                "\"IS_CHECK\" INTEGER," + // 12: isCheck
                "\"TIMESTAMP\" INTEGER);"); // 13: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Colleague\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Colleague entity) {
        stmt.clearBindings();
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(1, uid);
        }
 
        String uno = entity.getUno();
        if (uno != null) {
            stmt.bindString(2, uno);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(5, mobile);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(6, email);
        }
 
        String avatarid = entity.getAvatarid();
        if (avatarid != null) {
            stmt.bindString(7, avatarid);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(8, type);
        }
 
        String spelling = entity.getSpelling();
        if (spelling != null) {
            stmt.bindString(9, spelling);
        }
 
        String firstletter = entity.getFirstletter();
        if (firstletter != null) {
            stmt.bindString(10, firstletter);
        }
 
        Integer dataType = entity.getDataType();
        if (dataType != null) {
            stmt.bindLong(11, dataType);
        }
 
        String headName = entity.getHeadName();
        if (headName != null) {
            stmt.bindString(12, headName);
        }
 
        Boolean isCheck = entity.getIsCheck();
        if (isCheck != null) {
            stmt.bindLong(13, isCheck ? 1L: 0L);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(14, timestamp);
        }
    }

    @Override
    protected void attachEntity(Colleague entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Colleague readEntity(Cursor cursor, int offset) {
        Colleague entity = new Colleague( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // uid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // uno
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // username
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sex
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // mobile
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // email
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // avatarid
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // type
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // spelling
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // firstletter
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // dataType
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // headName
            cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12) != 0, // isCheck
            cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Colleague entity, int offset) {
        entity.setUid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUno(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUsername(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSex(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMobile(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmail(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAvatarid(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setType(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setSpelling(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFirstletter(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDataType(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setHeadName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setIsCheck(cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12) != 0);
        entity.setTimestamp(cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Colleague entity, long rowId) {
        return entity.getUid();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Colleague entity) {
        if(entity != null) {
            return entity.getUid();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}