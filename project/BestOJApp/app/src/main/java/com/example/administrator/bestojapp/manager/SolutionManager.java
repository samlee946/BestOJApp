package com.example.administrator.bestojapp.manager;

import android.content.Context;

import java.util.List;

import database.exam.solution.Solution;
import database.exam.solution.SolutionDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/19 0019.
 */
public interface SolutionManager {

    /**
     * 初始化
     * @param context
     */
    public void init(Context context);


    /**
     * 往本地数据库添加提交记录
     * @param solutionList
     */
    public void addSolution(Solution solution);

    /**
     * 在本地数据库通过提交记录编号搜索提交记录
     * @param problemId
     * @return
     */
    public List<Solution> searchByParentId(Long solutionId) ;

    /**
     * 用于删除提交记录本地的数据表
     */
    public void dropTable();

    /**
     * 用于创建提交记录本地的数据表
     */
    public void createTable();
}
