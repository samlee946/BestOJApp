package com.example.administrator.bestojapp.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import database.exam.discuss.Discuss;
import database.exam.solution.Solution;
import database.exam.solution.list.SolutionList;
import database.example.TreeNode2;
import com.example.administrator.bestojapp.Bean.Discusses;
import com.example.administrator.bestojapp.Bean.ExamList;
import com.example.administrator.bestojapp.Bean.ExamPaper;
import com.example.administrator.bestojapp.Bean.OJTreeNodeJavaBean;
import com.example.administrator.bestojapp.Bean.SolutionListJavaBean;
import com.example.administrator.bestojapp.manager.impl.DiscussManagerImpl;
import com.example.administrator.bestojapp.manager.impl.ExamPaperManagerImpl;
import com.example.administrator.bestojapp.manager.impl.SolutionListManagerImpl;
import com.example.administrator.bestojapp.manager.impl.SolutionManagerImpl;
import com.example.administrator.bestojapp.web.WebService;

import com.google.gson.Gson;

import com.example.administrator.bestojapp.manager.impl.ProblemManagerImpl;
import com.example.administrator.bestojapp.manager.impl.TreeNodeManagerImpl;
import database.problem.Problem;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

/**
 * Created by Administrator on 2016/4/5 0005.
 */
public class AccessManager {

    private TreeNodeManager     treeNodeManager;
    private ProblemManager      problemManager;
    private ExamPaperManager    examPaperManager;
    private DiscussManager      discussManager;
    private SolutionListManager solutionListManager;
    private SolutionManager     solutionManager;

    private Integer echo = -1;

    /** 服务器返回的字符串 */
    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";

    private Context context;

    WebService webService;

    public AccessManager(Context context, WebService webService) {
        this.context = context;
        this.webService = webService;
        this.echo = -1;
        treeNodeManager     = new TreeNodeManagerImpl();
        treeNodeManager.init(context);
        problemManager      = new ProblemManagerImpl();
        problemManager.init(context);
        examPaperManager    = new ExamPaperManagerImpl();
        examPaperManager.init(context);
        discussManager      = new DiscussManagerImpl();
        discussManager.init(context);
        solutionListManager = new SolutionListManagerImpl();
        solutionListManager.init(context);
        solutionManager     = new SolutionManagerImpl();
        solutionManager.init(context);
    }

    /**
     * 通过父亲结点的id获取子结点并存入本地数据库
     * @param parentId
     * @return 状态码
     * @throws HttpClientErrorException
     * @throws ResourceAccessException
     * @throws HttpServerErrorException
     */
    public List<TreeNode2> getTreeNodeByParentID(Long parentId)
            throws HttpClientErrorException, ResourceAccessException, HttpServerErrorException {
        List<TreeNode2> ret;
        try {
            echo = -1;
            response = webService.getOffspringByParentId(token, parentId);
            Log.d("response", "getTreeNodeByParentID: " + response);
            Gson gson = new Gson();
            OJTreeNodeJavaBean ojTreeNodeJavaBean = gson.fromJson(response, OJTreeNodeJavaBean.class);
            echo = ojTreeNodeJavaBean.getEcho();
            if (echo == 0) {
                TreeNode2[] treeNodes = ojTreeNodeJavaBean.getNotes();
                for (TreeNode2 treeNode2 : treeNodes) {
                    /** 把读取到的结点保存到数据库里 */
                    treeNodeManager.addTreeNode(treeNode2);
                }
            }
        } catch (Exception e) {

        } finally { /** 最终从数据库中读取 */
            ret = treeNodeManager.searchByParentId(parentId);
        }
        return ret;
    }

    /**
     * 通过题目id获取题目的详细信息并存入本地数据库
     * @param problemId
     * @return 状态码
     * @throws HttpClientErrorException
     * @throws ResourceAccessException
     * @throws HttpServerErrorException
     */
    public List<Problem> getProblemByProblemId(Long problemId)
            throws HttpClientErrorException, ResourceAccessException, HttpServerErrorException {
        List<Problem> problems;
        try {
            this.echo = -1;
            response = webService.getProblemByProblemId(token, problemId);
            Gson gson = new Gson();
            Problem problem = gson.fromJson(response, Problem.class);
            problemManager.addProblem(problem);
        } catch (Exception e) {

        } finally { /** 最终从数据库中读取 */
            problems = problemManager.searchByProblemId(problemId);
        }
        return problems;
    }

    /**
     * 通过题目id获取用户对于某一题的提交记录
     * type == 3 || 4 时为获取考试的提交记录
     *                    其他为获取平时题目的提交记录
     * @param problemId
     */
    public SolutionList[] getListOfUserByProblemId(int type, Long problemId, Long examPaperId) {
        SolutionList[] solutionLists = null;
        try {
            this.echo = -1;
            if(type == 3 || type == 4) {
                response = webService.getExamSolution(token, examPaperId);
            }
            else {
                response = webService.getListOfUserByProblemId(token, problemId);
            }
            Log.d("solution", "getListOfUserByProblemId: " + response);
            Gson gson = new Gson();
            SolutionListJavaBean solutionListJavaBean = gson.fromJson(response, SolutionListJavaBean.class);
            echo = solutionListJavaBean.getEcho();
            if(echo == 0) {
                solutionLists = solutionListJavaBean.getNotes();
                for(SolutionList solutionList : solutionLists) {
                    solutionListManager.addSolutionList(solutionList);
                }
            }
        } catch (Exception e) {

        } finally { /** 最终从数据库中读取 */
            if (echo != 0) {
                List<SolutionList> tempSolutionList = solutionListManager.searchBySolutionListId(problemId);
                solutionLists = new SolutionList[tempSolutionList.size()];
                tempSolutionList.toArray(solutionLists);
                echo = 0;
            }
        }
        return solutionLists;
    }

    /**
     * 通过提交记录id获取用户的提交详情
     * @param solutionId
     */
    public Solution getBySolutionId(Long solutionId) {
        Solution solution = null;
        try {
            this.echo = -1;
            response = webService.getBySolutionId(token, solutionId);
            Log.d("AccessManagerSolution", response);
            Gson gson = new Gson();
            solution = gson.fromJson(response, Solution.class);
            echo = solution.getEcho();
        } catch (Exception e) {

        } finally { /** 最终从数据库中读取 */
            if(solutionManager.searchByParentId(solutionId).size() > 0) {
                solution = solutionManager.searchByParentId(solutionId).get(0);
            }
        }
        return solution;
    }

    /**
     * 通过题目id获取当前题目下的所有讨论
     * @param problemID
     */
    public List<Discuss> getDiscussByProblemId(Long problemID) {
        List<Discuss> discussList = null;
        Discusses discusses;
        try {
            this.echo = -1;
            response = webService.getDiscussByProblemId(problemID);
            Log.d("response", "getDiscussByProblemId: " + response);
            Gson gson = new Gson();
            discusses = gson.fromJson(response, Discusses.class);
            echo = discusses.getEcho();
            discussManager.deleteDiscussByProblemId(problemID);
            for(Discuss discuss : discusses.getDiscusses()) {
                discussManager.addDiscuss(discuss);
            }
        } catch (Exception e) {

        } finally {
            discussList  = discussManager.getDiscussByProblemId(problemID);
        }
        return discussList;
    }

    /**
     * 获取当前用户参加的所有考试
     */
    public ExamList getExamList() {
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        ExamList examList;
        try {
            this.echo = -1;
            response = webService.getExamListFromServer(token);
            Gson gson = new Gson();
            examList = gson.fromJson(response, ExamList.class);
            sPrefs.edit().putString("ExamList", response).commit();
            Log.d("solution", "getExamList: " + examList.toString());
            echo = examList.getEcho();
        } catch (Exception e) {

        } finally { /** 从本地获取 */
            response = sPrefs.getString("ExamList", null);
            Log.d("response", "getExamList: " + response);
            Gson gson = new Gson();
            examList = gson.fromJson(response, ExamList.class);
            if(examList != null) {
                Log.d("solution", "**getExamList: " + examList.toString());
                echo = examList.getEcho();
            }
        }
        return examList;
    }

    /**
     * 通过考试id获取考试详情
     */
    public ExamPaper getExamPaper(Long examPaperId) {
        ExamPaper examPaper = null;
        try {
            this.echo = -1;
            response = webService.getExamPaper(token, examPaperId);
            Gson gson = new Gson();
            examPaper = gson.fromJson(response, ExamPaper.class);
            echo = examPaper.getEcho();
            database.exam.paper.ExamPaper examPaperToSave = new database.exam.paper.ExamPaper(examPaper.getId(), response);
            examPaperManager.addExamPaper(examPaperToSave);
        } catch (Exception e) {

        } finally {
            database.exam.paper.ExamPaper localExamPaper = examPaperManager.getExamPaperByPaperId(examPaperId);
            if(localExamPaper != null) {
                Gson gson = new Gson();
                examPaper = gson.fromJson(localExamPaper.getExamPaper(), ExamPaper.class);
                if(examPaper != null) {
                    echo = examPaper.getEcho();
                }
            }
            else examPaper = null;
        }
        return examPaper;
    }

    public void deleteTreeNode() {
        echo = -1;
        treeNodeManager.dropTable();
        treeNodeManager.createTable();
    }

    public Integer getEcho() {
        return echo;
    }
}
