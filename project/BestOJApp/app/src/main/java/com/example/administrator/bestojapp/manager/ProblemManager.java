package com.example.administrator.bestojapp.manager;

import android.content.Context;

import database.problem.Problem;

import java.util.List;

/**
 * Created by Administrator on 2016/4/6 0006.
 */
public interface ProblemManager {

    /**
     * init
     * @param context
     */
    public void init(Context context);

    /**
     * Add a problem into local database
     * @param problem
     */
    public void addProblem(Problem problem);

    /**
     * search problem in local database by problemId
     * @param problemId
     * @return
     */
    public List<Problem> searchByProblemId(Long problemId);
}
