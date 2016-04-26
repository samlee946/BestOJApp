package com.unlimited.appserver.webapp.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hibernate.Hibernate;

import com.unlimited.oj.Constants;
import com.unlimited.oj.dao.support.Page;
import com.unlimited.oj.model.LoginLog;
import com.unlimited.oj.model.User;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.util.ApplicationConfig;
import com.unlimited.oj.util.Tool;
import com.unlimited.oj.webapp.action.BaseAction;
import com.unlimited.oj.webapp.filter.URLDistributeFilter;
import com.unlimited.oj.webapp.filter.URLDistributeFilter;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;


/**
 * 
 * @ClassName: AppUserAction 
 * @Description: 用于响应app发到服务器的请求
 * @author Sam Lee
 * @date 2016-4-26 下午11:08:23 
 * @email samlee946@gmail.com
 *
 */
public class AppUserAction extends BaseAction
{
	/**
	 * 序列化
	 */
    private static final long serialVersionUID = 677614418712115191L;
    
    /**
     * 用于返回信息给APP
     */
    public String returnString;
    
    /**
     * APP传过来的用户的token
     */
    public String token;
    
    /**
     * APP传过来的提交记录ID
     */
    public String solutionId;
    
    /**
     * APP传过来的题目ID
     */
    public String problemId;
    
    /**
     * OJ的基础地址
     */
    public String url = "http://172.26.14.60:8000/uoj/";
//    public String url = "http://acm.scau.edu.cn:8000/uoj/";

    /**
     * Grab the entity from the database before populating with request
     * parameters
     */
    public void prepare()
    {
		int page = 1, sort = 1, order = 2;
		try {
			page = Integer.parseInt(getRequest().getParameter("page"));
		} catch (Exception e) { /* not need handle */
		}
		try {
			sort = Integer.parseInt(getRequest().getParameter("sort"));
		} catch (Exception e) { /* not need handle */
		}
		try {
			order = Integer.parseInt(getRequest().getParameter("order"));
		} catch (Exception e) { /* not need handle */
		}
		try {
			token = getRequest().getParameter("token");
        } catch (Exception e) { /*not need handle */ }
		try {
			solutionId = getRequest().getParameter("solutionId");
        } catch (Exception e) { /*not need handle */ }
		try {
			problemId = getRequest().getParameter("problemId");
        } catch (Exception e) { /*not need handle */ }
        
        super.prepare();
    }
    
    /**
     * 
     * @Title: user_CheckNetwork_PUBLIC 
     * @Description: 用于APP测试是否和服务器连接 
     * @param @return
     * @return String
     * @throws
     */
    public String user_CheckNetwork_PUBLIC() {
    	returnString = "网络已连接";
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_GetBySolutionId_PUBLIC 
     * @Description: 通过APP传输的提交记录ID获取提交记录的详细信息  
     * @param @return
     * @return String
     * @throws
     */
    public String user_GetBySolutionId_PUBLIC() {
    	String currentURL = "app_common_solution_getBySolutionId.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token + "&solutionId=" + solutionId;//需要的参数
    	try {
			getResponse().sendRedirect(url + currentURL + parameter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_GetProblemByProblemId_PUBLIC 
     * @Description: 通过APP传输的题目ID获取题目的详细信息 
     * @param @return
     * @return String
     * @throws
     */
    public String user_GetProblemByProblemId_PUBLIC() {
    	String currentURL = "app_common_problem_getProblemByProblemId.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token + "&problemId=" + problemId;//需要的参数
    	try {
			getResponse().sendRedirect(url + currentURL + parameter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    public String getReturnString() {
		return returnString;
	}
}
