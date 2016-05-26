package com.example.administrator.bestojapp.manager.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.bestojapp.manager.SolutionListManager;

import java.util.List;

import database.exam.solution.list.DaoMaster;
import database.exam.solution.list.DaoSession;
import database.exam.solution.list.SolutionList;
import database.exam.solution.list.SolutionListDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class SolutionListManagerImpl implements SolutionListManager{

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context) {
        this.context = context;
        setupDatabase();
        getSolutionListDao();
    }

    public void addSolutionList(SolutionList solutionList) {
        QueryBuilder queryBuilder = getSolutionListDao().queryBuilder()
                .where(SolutionListDao.Properties.Id.eq(solutionList.getId()));
        if(queryBuilder.list().isEmpty()) {
            getSolutionListDao().insert(solutionList);
        }
    }

    public List<SolutionList> searchBySolutionListId(Long problemId) {
        QueryBuilder queryBuilder = getSolutionListDao().queryBuilder()
                .where(SolutionListDao.Properties.ProblemId.eq(problemId));
        return (List<SolutionList>) queryBuilder.list();
    }

    public void dropTable() {
        getSolutionListDao().dropTable(db, true);
    }

    public void createTable() {
        getSolutionListDao().createTable(db, true);
    }

    private SolutionListDao getSolutionListDao() {
        return daoSession.getSolutionListDao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "solutionList-db", null);
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
