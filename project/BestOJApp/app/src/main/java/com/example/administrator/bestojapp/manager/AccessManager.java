package com.example.administrator.bestojapp.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import database.exam.discuss.Discuss;
import database.exam.solution.Solution;
import database.example.TreeNode2;
import com.example.administrator.bestojapp.Bean.Discusses;
import com.example.administrator.bestojapp.Bean.ExamList;
import com.example.administrator.bestojapp.Bean.ExamPaper;
import com.example.administrator.bestojapp.Bean.OJTreeNodeJavaBean;
import com.example.administrator.bestojapp.Bean.SolutionListJavaBean;
import com.example.administrator.bestojapp.manager.impl.DiscussManagerImpl;
import com.example.administrator.bestojapp.manager.impl.ExamPaperManagerImpl;
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

    private TreeNodeManagerImpl treeNodeManager;
    private ProblemManagerImpl problemManager;
    private ExamPaperManagerImpl examPapaerManager;
    private DiscussManagerImpl discussManager;

    private Integer echo = -1;

    /** 服务器返回的字符串 */
    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";

    private Context context;

    /** 提交记录列表 */
    private database.exam.solution.list.SolutionList[] solutionLists;

    /** 详细提交记录 */
    private Solution solution;

    /** 讨论列表 */
    private Discusses discusses;

    /** 考试列表 */
    private ExamList examList;

    /** 考试详情 */
    private ExamPaper examPaper;

    WebService webService;

    public AccessManager(Context context, WebService webService) {
        this.context = context;
        this.webService = webService;
        this.echo = -1;
        treeNodeManager = new TreeNodeManagerImpl();
        treeNodeManager.init(context);
        problemManager = new ProblemManagerImpl();
        problemManager.init(context);
        examPapaerManager = new ExamPaperManagerImpl();
        examPapaerManager.init(context);
        discussManager = new DiscussManagerImpl();
        discussManager.init(context);
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
     * @param problemId
     */
    public void getListOfUserByProblemId(Long problemId) {
        this.echo = -1;
        response = webService.getListOfUserByProblemId(token, problemId);
        Gson gson = new Gson();
        SolutionListJavaBean solutionListJavaBean = gson.fromJson(response, SolutionListJavaBean.class);
        echo = solutionListJavaBean.getEcho();
        if(echo == 0) {
            solutionLists = solutionListJavaBean.getNotes();
        }
    }

    /**
     * 通过提交记录id获取用户的提交详情
     * @param solutionId
     */
    public void getBySolutionId(Long solutionId) {
        this.echo = -1;
        response = webService.getBySolutionId(token, solutionId);
        Gson gson = new Gson();
        solution = gson.fromJson(response, Solution.class);
        echo = solution.getEcho();
    }

    /**
     * 通过题目id获取当前题目下的所有讨论
     * @param problemID
     */
    public List<Discuss> getDiscussByProblemId(Long problemID) {
        List<Discuss> discussList = null;
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
    public void getExamListFromServer() {
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            this.echo = -1;
            Log.d("solution", "*getExamListFromServer: ");
            response = webService.getExamListFromServer(token);
            Log.d("solution", "**getExamListFromServer: " + response);
            Gson gson = new Gson();
            this.examList = gson.fromJson(response, ExamList.class);
            sPrefs.edit().putString("ExamList", response).commit();
            Log.d("solution", "**getExamListFromServer: " + this.examList.toString());
            echo = this.examList.getEcho();
        } catch (Exception e) {

        } finally { /** 从本地获取 */
            response = sPrefs.getString("ExamList", null);
            Log.d("response", "getExamListFromServer: " + response);
            Gson gson = new Gson();
            this.examList = gson.fromJson(response, ExamList.class);
            if(this.examList != null) {
                Log.d("solution", "**getExamListFromServer: " + this.examList.toString());
                echo = this.examList.getEcho();
            }
        }
    }

    /**
     * 通过考试id获取考试详情
     */
    public void getExamPaperFromServer(Long examPaperId) {
        try {
            this.echo = -1;
            response = webService.getExamPaper(token, examPaperId);
            Gson gson = new Gson();
            examPaper = gson.fromJson(response, ExamPaper.class);
            echo = examPaper.getEcho();
            database.exam.paper.ExamPaper examPaperToSave = new database.exam.paper.ExamPaper(examPaper.getId(), response);
            examPapaerManager.addExamPaper(examPaperToSave);
        } catch (Exception e) {

        } finally {
            database.exam.paper.ExamPaper localExamPaper = examPapaerManager.getExamPaperByPaperId(examPaperId);
            if(localExamPaper != null) {
                Gson gson = new Gson();
                examPaper = gson.fromJson(localExamPaper.getExamPaper(), ExamPaper.class);
                if(examPaper != null) {
                    echo = examPaper.getEcho();
                }
            }
            else examPaper = null;
        }
    }

    /**
     * 通过考试id获取提交记录
     * @param examPaperId
     */
    public void getExamSolutionFromServer(Long examPaperId) {
        this.echo = -1;
        response = webService.getExamSolution(token, examPaperId);
        Gson gson = new Gson();
        SolutionListJavaBean solutionListJavaBean = gson.fromJson(response, SolutionListJavaBean.class);
        echo = solutionListJavaBean.getEcho();
        if(echo == 0) {
            solutionLists = solutionListJavaBean.getNotes();
        }
    }

    public boolean isDiscussDownloaded() {
        return echo == 0;
    }

    public boolean isTreeNodeDownloaded(Long parentID) {
        return treeNodeManager.isTreeNodeDownloaded(parentID);
    }

    public boolean isProblemDownloaded(Long problemId) {
        return problemManager.isProblemDownloaded(problemId);
    }

    public void deleteTreeNode() {
        echo = -1;
        treeNodeManager.dropTable();
        treeNodeManager.createTable();
    }

    public database.exam.solution.list.SolutionList[] getSolutionLists() {
        return solutionLists;
    }

    public Integer getEcho() {
        return echo;
    }

    public void setEcho(Integer echo) {
        this.echo = echo;
    }

    public String getResponse() {
        return response;
    }

    public Solution getSolution() {
        return solution;
    }

    public Discusses getDiscusses() {
        return discusses;
    }

    public ExamList getExamList() {
        return examList;
    }

    public ExamPaper getExamPaper() {
        return examPaper;
    }
}
