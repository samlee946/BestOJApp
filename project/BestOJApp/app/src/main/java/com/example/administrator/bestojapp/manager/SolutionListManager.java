package com.example.administrator.bestojapp.manager;

import android.content.Context;

import java.util.List;

import database.exam.solution.list.SolutionList;
import database.exam.solution.list.SolutionListDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public interface SolutionListManager {

    /**
     * 初始化
     * @param context
     */
    public void init(Context context);

    /**
     * 往本地数据库添加提交记录列表
     * @param solutionList
     */
    public void addSolutionList(SolutionList solutionList);

    /**
     * 在本地数据库通过提交记录列表编号搜索提交记录列表
     * @param problemId
     * @return
     */
    public List<SolutionList> searchBySolutionListId(Long problemId);

    /**
     * 用于删除提交记录列表本地的数据表
     */
    public void dropTable();

    /**
     * 用于创建提交记录列表本地的数据表
     */
    public void createTable();

}
