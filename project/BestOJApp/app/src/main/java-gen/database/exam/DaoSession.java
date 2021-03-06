package database.exam;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig examDaoConfig;

    private final ExamDao examDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        examDaoConfig = daoConfigMap.get(ExamDao.class).clone();
        examDaoConfig.initIdentityScope(type);

        examDao = new ExamDao(examDaoConfig, this);

        registerDao(Exam.class, examDao);
    }
    
    public void clear() {
        examDaoConfig.getIdentityScope().clear();
    }

    public ExamDao getExamDao() {
        return examDao;
    }

}
