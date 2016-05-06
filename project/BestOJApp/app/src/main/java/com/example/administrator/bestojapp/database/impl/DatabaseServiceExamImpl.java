package com.example.administrator.bestojapp.database.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.exam.ExamDao;
import com.exam.DaoMaster;
import com.exam.DaoSession;
import com.example.administrator.bestojapp.database.DatabaseServiceExam;

/**
 * Created by Administrator on 2016/5/6 0006.
 * 实现DatabaseServiceExam接口
 */
public class DatabaseServiceExamImpl implements DatabaseServiceExam {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public DatabaseServiceExamImpl(Context context) {
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
