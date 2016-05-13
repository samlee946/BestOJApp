package com.example.administrator.bestojapp.manager.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.bestojapp.manager.ExamPaperManager;

import java.util.List;

import database.exam.paper.ExamPaper;
import database.exam.paper.ExamPaperDao;
import database.exam.paper.DaoMaster;
import database.exam.paper.DaoSession;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class ExamPaperManagerImpl implements ExamPaperManager {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context) {
        this.context = context;
        setupDatabase();
        getExamPaperDao();
    }

    public ExamPaper getExamPaperByPaperId(Long examPaperId) {
        QueryBuilder queryBuilder = getExamPaperDao().queryBuilder()
                .where(ExamPaperDao.Properties.Id.eq(examPaperId));
        List<ExamPaper> examPapers =  queryBuilder.list();
        if(examPapers.isEmpty()) return null;
        else return examPapers.get(0);
    }

    public void addExamPaper(ExamPaper examPaper) {
        getExamPaperDao().insert(examPaper);
    }

    private ExamPaperDao getExamPaperDao() {
        return daoSession.getExamPaperDao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "examPaper-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
