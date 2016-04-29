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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hibernate.Hibernate;

import com.unlimited.appserver.dao.exception.DiscussNotFoundException;
import com.unlimited.appserver.model.AppUser;
import com.unlimited.appserver.model.Discuss;
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
	/** 序列化 */
    private static final long serialVersionUID = 677614418712115191L;
    
    /** 用于返回信息给APP */
    private String returnString;
    
    /** APP传过来的用户的token */
    private String token;
    
    /** APP传过来的提交记录ID */
    private String solutionId;
    
    /** APP传过来的题目ID */
    private String problemId;
    
    /** APP传过来的父亲结点ID */
    private String parentId;
    
    /** APP传过来的用户名 */
    private String username;
    
    /** APP传过来的密码 */
    private String password;
    
    /** APP传过来的讨论标题 */
    private String discussTitle;
    
    /** APP传过来的讨论正文 */
    private String discussContent;
    
    /** APP传过来的讨论id*/
    private String discussId;
    
    /** OJ的基础地址 */
    private String url = "http://172.26.14.60:8000/uoj/";
//    private String url = "http://acm.scau.edu.cn:8000/uoj/";

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
		try {
			parentId = getRequest().getParameter("parentId");
        } catch (Exception e) { /*not need handle */ }
		try {
			username = getRequest().getParameter("username");
        } catch (Exception e) { /*not need handle */ }
		try {
			password = getRequest().getParameter("password");
        } catch (Exception e) { /*not need handle */ }
		try {
			discussTitle = getRequest().getParameter("title");
        } catch (Exception e) { /*not need handle */ }
		try {
			discussContent = getRequest().getParameter("content");
        } catch (Exception e) { /*not need handle */ }
		try {
			discussId = getRequest().getParameter("discussId");
        } catch (Exception e) { /*not need handle */ }
        
        super.prepare();
    }
    
    /**
     * 
     * @Title: user_CheckNetwork_PUBLIC 
     * @Description: 用于APP测试是否和服务器连接 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_CheckNetwork_PUBLIC() {
    	returnString = "网络已连接";
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_GetBySolutionId_PUBLIC 
     * @Description: 通过APP传输过来的提交记录ID获取提交记录的详细信息  
     * @return user_echo_PUBLIC.jsp
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
     * @Description: 通过APP传输过来的题目ID获取题目的详细信息 
     * @return user_echo_PUBLIC.jsp
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
    
    /**
     * 
     * @Title: user_GetOffspringByParentId 
     * @Description: 通过APP传输过来的父结点ID获得该结点所有子孙结点信息 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_GetOffspringByParentId_PUBLIC() {
    	String currentURL = "app_common_ojTreeNode_getOffspringByParentId.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token + "&parentId=" + problemId;//需要的参数
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
     * @Title: user_GetListOfUserByProblemId_PUBLIC 
     * @Description: 通过APP传输过来的题目ID获取用户对某一题的所有提交
     * @return user_echo_PUBLIC.jsp
     */
    public String user_GetListOfUserByProblemId_PUBLIC() {
    	String currentURL = "app_common_solution_getListOfUserByProblemId.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token + "&problemId=" + problemId;//需要的参数
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
     * @Title: user_Register_PUBLIC 
     * @Description: 注册用户 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_Register_PUBLIC() {
    	log.debug("Register:" + username + " " + password + " " + token + " ");
    	try {
    		AppUser user = new AppUser(username, password, token);
    		if(appUserManager == null) log.debug("-------------------MANAGER NULL------------------");
			appUserManager.saveAppUser(user);
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_login_PUBLIC 
     * @Description: 用户登陆 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_login_PUBLIC() {
    	Long userId = appUserManager.getUserIdByUsername(username);
    	System.out.println(userId+username+password+"登陆");
    	if(appUserManager.isUserExist(username, password)) {
    		Cookie cookie = new Cookie("USERID", "" + userId);
    		getResponse().addCookie(cookie);
        	returnString = "登陆成功";
    	}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_testLogin_PUBLIC 
     * @Description: 测试是否登陆成功 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_testLogin_PUBLIC() {
		Cookie[] cookies = getRequest().getCookies();
		boolean flag = false;
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				System.out.println("cookie:" + cookie.getName() + " " + cookie.getValue());
				if(cookie.getName().equals("USERID") && !cookie.getValue().equals("null")) {
					flag = true;
					System.out.println("登陆成功辣!");
					break;
				}
			}
		}
		if(flag == false) {
			System.out.println("登陆失败!");
		}
		System.out.println("测试登陆完毕!");
    	returnString = "测试登陆完毕!";
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_GetDiscussByProblemId_PUBLIC 
     * @Description: 获取某个题目下的所有讨论 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_GetDiscussByProblemId_PUBLIC() {
    	try {
			returnString = discussManager.getDiscussByProblemID(Long.parseLong(problemId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiscussNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    public String user_PostDiscuss_PUBLIC() {
    	Cookie[] cookies = getRequest().getCookies();
		boolean flag = false;
		Long userID = null;
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("USERID") && !cookie.getValue().equals("null")) {
					flag = true;
					userID = Long.parseLong(cookie.getValue());
					break;
				}
			}
		}
		if(flag == false) {
			returnString = "请先登陆!";
		}
		else {
			discussManager.postDiscuss(new Discuss(discussTitle, discussContent, Long.parseLong(problemId), userID));
			returnString = "发帖成功!";
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_removeDiscuss_PUBLIC 
     * @Description: 删除讨论 
     * @param @return
     * @return String
     * @throws
     */
    public String user_removeDiscuss_PUBLIC() {
    	discussManager.removeDiscuss(Long.parseLong(discussId));
		returnString = "删除成功!";
		return "returnAppData";
    }
    
    public String getReturnString() {
		return returnString;
	}
}
