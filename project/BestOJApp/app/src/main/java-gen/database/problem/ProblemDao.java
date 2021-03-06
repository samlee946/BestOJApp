package database.problem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PROBLEM.
*/
public class ProblemDao extends AbstractDao<Problem, Long> {

    public static final String TABLENAME = "PROBLEM";

    /**
     * Properties of entity Problem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property TimeLimit = new Property(2, Long.class, "timeLimit", false, "TIME_LIMIT");
        public final static Property MemoryLimit = new Property(3, Long.class, "memoryLimit", false, "MEMORY_LIMIT");
        public final static Property Description = new Property(4, String.class, "description", false, "DESCRIPTION");
        public final static Property Input = new Property(5, String.class, "input", false, "INPUT");
        public final static Property Output = new Property(6, String.class, "output", false, "OUTPUT");
        public final static Property SampleInput = new Property(7, String.class, "sampleInput", false, "SAMPLE_INPUT");
        public final static Property SampleOutput = new Property(8, String.class, "sampleOutput", false, "SAMPLE_OUTPUT");
        public final static Property Source = new Property(9, String.class, "source", false, "SOURCE");
        public final static Property Tip = new Property(10, String.class, "tip", false, "TIP");
    };


    public ProblemDao(DaoConfig config) {
        super(config);
    }
    
    public ProblemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PROBLEM' (" + //
                "'ID' INTEGER PRIMARY KEY ," + // 0: id
                "'TITLE' TEXT," + // 1: title
                "'TIME_LIMIT' INTEGER," + // 2: timeLimit
                "'MEMORY_LIMIT' INTEGER," + // 3: memoryLimit
                "'DESCRIPTION' TEXT," + // 4: description
                "'INPUT' TEXT," + // 5: input
                "'OUTPUT' TEXT," + // 6: output
                "'SAMPLE_INPUT' TEXT," + // 7: sampleInput
                "'SAMPLE_OUTPUT' TEXT," + // 8: sampleOutput
                "'SOURCE' TEXT," + // 9: source
                "'TIP' TEXT);"); // 10: tip
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PROBLEM'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Problem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        Long timeLimit = entity.getTimeLimit();
        if (timeLimit != null) {
            stmt.bindLong(3, timeLimit);
        }
 
        Long memoryLimit = entity.getMemoryLimit();
        if (memoryLimit != null) {
            stmt.bindLong(4, memoryLimit);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
 
        String input = entity.getInput();
        if (input != null) {
            stmt.bindString(6, input);
        }
 
        String output = entity.getOutput();
        if (output != null) {
            stmt.bindString(7, output);
        }
 
        String sampleInput = entity.getSampleInput();
        if (sampleInput != null) {
            stmt.bindString(8, sampleInput);
        }
 
        String sampleOutput = entity.getSampleOutput();
        if (sampleOutput != null) {
            stmt.bindString(9, sampleOutput);
        }
 
        String source = entity.getSource();
        if (source != null) {
            stmt.bindString(10, source);
        }
 
        String tip = entity.getTip();
        if (tip != null) {
            stmt.bindString(11, tip);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Problem readEntity(Cursor cursor, int offset) {
        Problem entity = new Problem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // timeLimit
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // memoryLimit
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // input
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // output
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // sampleInput
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // sampleOutput
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // source
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // tip
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Problem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTimeLimit(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setMemoryLimit(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setInput(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setOutput(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSampleInput(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSampleOutput(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSource(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTip(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Problem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Problem entity) {
        if(entity != null) {
            return entity.getId();
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
