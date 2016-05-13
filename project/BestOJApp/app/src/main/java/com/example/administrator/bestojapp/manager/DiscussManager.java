package com.example.administrator.bestojapp.manager;

import android.content.Context;

import java.util.List;

import database.exam.discuss.Discuss;
import database.exam.discuss.DiscussDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public interface DiscussManager {

    public void init(Context context);

    /**
     * 通过题目id获取讨论
     * @param problemId
     * @return
     */
    public List<Discuss> getDiscussByProblemId(Long problemId);

    /**
     * 往数据库中添加讨论
     * @param examPaper
     */
    public void addDiscuss(Discuss discuss);

    /**
     * 删除某条题目下所有的讨论 在联网更新的时候使用
     * @param problemId
     */
    public void deleteDiscussByProblemId(Long problemId);
}
