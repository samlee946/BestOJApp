package com.example.administrator.bestojapp.manager;

import android.content.Context;

import database.exam.paper.ExamPaper;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public interface ExamPaperManager {

    public void init(Context context) ;

    public void addExamPaper(ExamPaper examPaper);

    public ExamPaper getExamPaperByPaperId(Long examPaperId);
}
