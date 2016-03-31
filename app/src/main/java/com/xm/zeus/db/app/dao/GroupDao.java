package com.xm.zeus.db.app.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.xm.zeus.db.app.entity.Group;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Group".
*/
public class GroupDao extends AbstractDao<Group, String> {

    public static final String TABLENAME = "Group";

    /**
     * Properties of entity Group.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Uid = new Property(0, String.class, "uid", true, "UID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Avatarid = new Property(2, String.class, "avatarid", false, "AVATARID");
        public final static Property HeadName = new Property(3, String.class, "headName", false, "HEAD_NAME");
        public final static Property DataType = new Property(4, Integer.class, "dataType", false, "DATA_TYPE");
        public final static Property Timestamp = new Property(5, Long.class, "timestamp", false, "TIMESTAMP");
    };

    private DaoSession daoSession;


    public GroupDao(DaoConfig config) {
        super(config);
    }
    
    public GroupDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Group\" (" + //
                "\"UID\" TEXT PRIMARY KEY NOT NULL ," + // 0: uid
                "\"NAME\" TEXT," + // 1: name
                "\"AVATARID\" TEXT," + // 2: avatarid
                "\"HEAD_NAME\" TEXT," + // 3: headName
                "\"DATA_TYPE\" INTEGER," + // 4: dataType
                "\"TIMESTAMP\" INTEGER);"); // 5: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Group\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Group entity) {
        stmt.clearBindings();
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(1, uid);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String avatarid = entity.getAvatarid();
        if (avatarid != null) {
            stmt.bindString(3, avatarid);
        }
 
        String headName = entity.getHeadName();
        if (headName != null) {
            stmt.bindString(4, headName);
        }
 
        Integer dataType = entity.getDataType();
        if (dataType != null) {
            stmt.bindLong(5, dataType);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(6, timestamp);
        }
    }

    @Override
    protected void attachEntity(Group entity) {
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
    public Group readEntity(Cursor cursor, int offset) {
        Group entity = new Group( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // uid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // avatarid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // headName
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // dataType
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Group entity, int offset) {
        entity.setUid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAvatarid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHeadName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDataType(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setTimestamp(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(Group entity, long rowId) {
        return entity.getUid();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(Group entity) {
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