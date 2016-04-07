package com.example.administrator.bestojapp.api;

import android.content.Context;

import com.example.TreeNode2;
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

    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";

    private Context context;

    private SolutionList[] solutionLists;

    private SolutionDetailJavaBean solutionDetailJavaBean;

    WebService webService;

    public OJService(Context context, WebService webService) {
        this.context = context;
        this.webService = webService;
        this.echo = -1;
        databaseService = new DatabaseService(context);
        databaseServiceProblem = new DatabaseServiceProblem(context);
    }

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

    public Integer getProblemByProblemId(Long problemId)
            throws HttpClientErrorException, ResourceAccessException, HttpServerErrorException {
        synchronized (this) {
            response = webService.getProblemByProblemId(token, problemId);
            Gson gson = new Gson();
            Problem problem = gson.fromJson(response, Problem.class);
            databaseServiceProblem.addProblem(problem);
            return echo;
        }
    }

    public void getListOfUserByProblemId(Long problemId) {
        response = webService.getListOfUserByProblemId(token, problemId);
        Gson gson = new Gson();
        SolutionListJavaBean solutionListJavaBean = gson.fromJson(response, SolutionListJavaBean.class);
        echo = solutionListJavaBean.getEcho();
        if(echo == 0) {
            solutionLists = solutionListJavaBean.getNotes();
        }
    }

    public void getBySolutionId(Long solutionId) {
        response = webService.getBySolutionId(token, solutionId);
        Gson gson = new Gson();
        solutionDetailJavaBean = gson.fromJson(response, SolutionDetailJavaBean.class);
        echo = solutionDetailJavaBean.getEcho();
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
}
