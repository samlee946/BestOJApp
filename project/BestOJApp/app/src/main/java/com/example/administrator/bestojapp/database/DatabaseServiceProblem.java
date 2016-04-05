package com.example.administrator.bestojapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.problem.DaoMaster;
import com.problem.DaoSession;
import com.problem.Problem;
import com.problem.ProblemDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/4/6 0006.
 */
public class DatabaseServiceProblem {
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public DatabaseServiceProblem(Context context) {
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

    public boolean isProblemDownloaded(Long problemId) {
        if(searchByProblemId(problemId).isEmpty()) return false;
        else return true;
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
