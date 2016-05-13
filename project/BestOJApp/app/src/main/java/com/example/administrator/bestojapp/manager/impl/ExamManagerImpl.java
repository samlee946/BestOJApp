package com.example.administrator.bestojapp.manager.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import database.exam.ExamDao;
import database.exam.DaoMaster;
import database.exam.DaoSession;
import com.example.administrator.bestojapp.manager.ExamManager;

/**
 * Created by Administrator on 2016/5/6 0006.
 * 实现DatabaseServiceExam接口
 */
public class ExamManagerImpl implements ExamManager {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public ExamManagerImpl(Context context) {
        this.context = context;
        setupDatabase();
        getExamDao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "exam-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private ExamDao getExamDao() {
        return daoSession.getExamDao();
    }
}
