package com.example.administrator.bestojapp.api;

import android.content.Context;

import com.example.TreeNode2;
import com.example.administrator.bestojapp.Bean.DiscussJavaBean;
import com.example.administrator.bestojapp.Bean.Discusses;
import com.example.administrator.bestojapp.Bean.ExamList;
import com.example.administrator.bestojapp.Bean.ExamPaper;
import com.example.administrator.bestojapp.Bean.OJTreeNodeJavaBean;
import com.example.administrator.bestojapp.Bean.SolutionDetailJavaBean;
import com.example.administrator.bestojapp.Bean.SolutionList;
import com.example.administrator.bestojapp.Bean.SolutionListJavaBean;
import com.example.administrator.bestojapp.Bean.TreeNodeBean;
import com.example.administrator.bestojapp.database.DatabaseService;
import com.example.administrator.bestojapp.database.DatabaseServiceProblem;
import com.google.gson.Gson;
import com.problem.Problem;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/**
 * Created by Administrator on 2016/4/5 0005.
 */
public class OJService {

    private DatabaseService databaseService;
    private DatabaseServiceProblem databaseServiceProblem;

    private Integer echo = -1;

    /** 服务器返回的字符串 */
    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";

    private Context context;

    /** 提交记录列表 */
    private SolutionList[] solutionLists;

    /** 详细提交记录 */
    private SolutionDetailJavaBean solutionDetailJavaBean;

    /** 讨论列表 */
    private Discusses discusses;

    /** 考试列表 */
    private ExamList examList;

    /** 考试详情 */
    private ExamPaper examPaper;

    WebService webService;

    public OJService(Context context, WebService webService) {
        this.context = context;
        this.webService = webService;
        this.echo = -1;
        databaseService = new DatabaseService(context);
        databaseServiceProblem = new DatabaseServiceProblem(context);
    }

    /**
     * 通过父亲结点的id获取子结点并存入本地数据库
     * @param parentId
     * @return 状态码
     * @throws HttpClientErrorException
     * @throws ResourceAccessException
     * @throws HttpServerErrorException
     */
    public Integer getTreeNodeByParentID(Long parentId)
            throws HttpClientErrorException, ResourceAccessException, HttpServerErrorException {
        synchronized (this) {
            echo = -1;
            response = webService.getOffspringByParentId(token, parentId);
            Gson gson = new Gson();
            OJTreeNodeJavaBean ojTreeNodeJavaBean = gson.fromJson(response, OJTreeNodeJavaBean.class);
            echo = ojTreeNodeJavaBean.getEcho();
            if(echo == 0) {
                TreeNodeBean[] treeNodes = ojTreeNodeJavaBean.getNotes();
                for(TreeNodeBean treeNode : treeNodes) {
                    TreeNode2 treeNodeForDB = new TreeNode2(
                            treeNode.getId(),
                            treeNode.getParentId(),
                            treeNode.getOrder(),
                            treeNode.getProblemIdLinked(),
                            treeNode.getName(),
                            treeNode.getType()
                    );
                    databaseService.addTreeNode(treeNodeForDB);
                }
            }
            return echo;
        }
    }

    /**
     * 通过题目id获取题目的详细信息并存入本地数据库
     * @param problemId
     * @return 状态码
     * @throws HttpClientErrorException
     * @throws ResourceAccessException
     * @throws HttpServerErrorException
     */
    public Integer getProblemByProblemId(Long problemId)
            throws HttpClientErrorException, ResourceAccessException, HttpServerErrorException {
        synchronized (this) {
            this.echo = -1;
            response = webService.getProblemByProblemId(token, problemId);
            Gson gson = new Gson();
            Problem problem = gson.fromJson(response, Problem.class);
            databaseServiceProblem.addProblem(problem);
            return echo;
        }
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
        solutionDetailJavaBean = gson.fromJson(response, SolutionDetailJavaBean.class);
        echo = solutionDetailJavaBean.getEcho();
    }

    /**
     * 通过题目id获取当前题目下的所有讨论
     * @param problemID
     */
    public void getDiscussByProblemId(Long problemID) {
        this.echo = -1;
        response = webService.getDiscussByProblemId(problemID);
        Gson gson = new Gson();
        discusses = gson.fromJson(response, Discusses.class);
        echo = discusses.getEcho();
    }

    /**
     * 获取当前用户参加的所有考试
     */
    public void getExamListFromServer() {
        this.echo = -1;
        response = webService.getExamListFromServer(token);
        Gson gson = new Gson();
        examList = gson.fromJson(response, ExamList.class);
        echo = examList.getEcho();
    }

    /**
     * 通过考试id获取考试详情
     */
    public void getExamPaperFromServer(Long examPaperId) {
        this.echo = -1;
        response = webService.getExamPaper(token, examPaperId);
        Gson gson = new Gson();
        examPaper = gson.fromJson(response, ExamPaper.class);
        echo = examPaper.getEcho();
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
        return databaseService.isTreeNodeDownloaded(parentID);
    }

    public boolean isProblemDownloaded(Long problemId) {
        return databaseServiceProblem.isProblemDownloaded(problemId);
    }

    public void deleteTreeNode() {
        echo = -1;
        databaseService.dropTable();
        databaseService.createTable();
    }

    public SolutionList[] getSolutionLists() {
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

    public SolutionDetailJavaBean getSolutionDetailJavaBean() {
        return solutionDetailJavaBean;
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
