package database.message;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import database.message.Message;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MESSAGE.
*/
public class MessageDao extends AbstractDao<Message, Void> {

    public static final String TABLENAME = "MESSAGE";

    /**
     * Properties of entity Message.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", false, "ID");
        public final static Property Date = new Property(1, String.class, "date", false, "DATE");
        public final static Property Type = new Property(2, Integer.class, "type", false, "TYPE");
        public final static Property LinkedID = new Property(3, Long.class, "linkedID", false, "LINKED_ID");
        public final static Property ExtraMsg = new Property(4, String.class, "extraMsg", false, "EXTRA_MSG");
        public final static Property UserID = new Property(5, Long.class, "userID", false, "USER_ID");
    };


    public MessageDao(DaoConfig config) {
        super(config);
    }
    
    public MessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MESSAGE' (" + //
                "'ID' INTEGER," + // 0: id
                "'DATE' TEXT," + // 1: date
                "'TYPE' INTEGER," + // 2: type
                "'LINKED_ID' INTEGER," + // 3: linkedID
                "'EXTRA_MSG' TEXT," + // 4: extraMsg
                "'USER_ID' INTEGER);"); // 5: userID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MESSAGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Message entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(3, type);
        }
 
        Long linkedID = entity.getLinkedID();
        if (linkedID != null) {
            stmt.bindLong(4, linkedID);
        }
 
        String extraMsg = entity.getExtraMsg();
        if (extraMsg != null) {
            stmt.bindString(5, extraMsg);
        }
 
        Long userID = entity.getUserID();
        if (userID != null) {
            stmt.bindLong(6, userID);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Message readEntity(Cursor cursor, int offset) {
        Message entity = new Message( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // date
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // linkedID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // extraMsg
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // userID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Message entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setType(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setLinkedID(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setExtraMsg(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserID(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Message entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Message entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
