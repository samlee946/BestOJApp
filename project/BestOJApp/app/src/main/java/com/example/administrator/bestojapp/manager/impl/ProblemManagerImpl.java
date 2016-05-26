package com.example.administrator.bestojapp.manager.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import com.example.administrator.bestojapp.manager.ProblemManager;
import database.problem.DaoMaster;
import database.problem.DaoSession;
import database.problem.Problem;
import database.problem.ProblemDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/4/6 0006.
 */
public class ProblemManagerImpl implements ProblemManager{
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context) {
        this.context = context;
        setupDatabase();
        getProblemDao();
    }

    public void addProblem(Problem problem) {
        getProblemDao().insert(problem);
    }

    public List<Problem> searchByProblemId(Long problemId) {
        QueryBuilder queryBuilder = getProblemDao().queryBuilder()
                .where(ProblemDao.Properties.Id.eq(problemId));
        return (List<Problem>) queryBuilder.list();
    }

    private ProblemDao getProblemDao() {
        return daoSession.getProblemDao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "problem-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
