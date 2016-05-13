package com.example.administrator.bestojapp.manager.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.bestojapp.manager.DiscussManager;

import java.util.List;

import database.exam.discuss.DaoMaster;
import database.exam.discuss.DaoSession;
import database.exam.discuss.Discuss;
import database.exam.discuss.DiscussDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class DiscussManagerImpl implements DiscussManager {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context) {
        this.context = context;
        setupDatabase();
        getDiscussDao();
    }

    public List<Discuss> getDiscussByProblemId(Long problemId) {
        QueryBuilder queryBuilder = getDiscussDao().queryBuilder()
                .where(DiscussDao.Properties.ProblemId.eq(problemId));
        List<Discuss> discusses =  queryBuilder.list();
        if(discusses.isEmpty()) return null;
        else return discusses;
    }

    public void deleteDiscussByProblemId(Long problemId) {
        List<Discuss> discusses =  getDiscussByProblemId(problemId);
        if(discusses != null) {
            for(Discuss discuss : discusses) {
                getDiscussDao().deleteByKey(discuss.getId());
            }
        }
    }

    public void addDiscuss(Discuss discuss) {
        getDiscussDao().insert(discuss);
    }

    private DiscussDao getDiscussDao() {
        return daoSession.getDiscussDao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "discuss-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
