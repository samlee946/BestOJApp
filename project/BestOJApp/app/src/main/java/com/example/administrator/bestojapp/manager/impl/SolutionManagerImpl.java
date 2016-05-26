package com.example.administrator.bestojapp.manager.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.bestojapp.manager.SolutionManager;

import java.util.List;

import database.exam.solution.DaoMaster;
import database.exam.solution.DaoSession;
import database.exam.solution.Solution;
import database.exam.solution.SolutionDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public class SolutionManagerImpl implements SolutionManager {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context) {
        this.context = context;
        setupDatabase();
        getSolutionDao();
    }

    public void addSolution(Solution solution) {
        QueryBuilder queryBuilder = getSolutionDao().queryBuilder()
                .where(SolutionDao.Properties.Id.eq(solution.getId()));
        if(queryBuilder.list().isEmpty()) {
            getSolutionDao().insert(solution);
        }
    }

    public List<Solution> searchByParentId(Long solutionId) {
        QueryBuilder queryBuilder = getSolutionDao().queryBuilder()
                .where(SolutionDao.Properties.Id.eq(solutionId));
        return (List<Solution>) queryBuilder.list();
    }

    public void dropTable() {
        getSolutionDao().dropTable(db, true);
    }

    public void createTable() {
        getSolutionDao().createTable(db, true);
    }

    private SolutionDao getSolutionDao() {
        return daoSession.getSolutionDao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "solution-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }
}

