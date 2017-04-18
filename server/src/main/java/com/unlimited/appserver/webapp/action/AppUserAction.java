package com.unlimited.appserver.webapp.action;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Hibernate;

import sun.rmi.runtime.Log;
import sun.security.provider.MD5;

import com.unlimited.appserver.dao.exception.DiscussNotFoundException;
import com.unlimited.appserver.model.Discuss;
import com.unlimited.appserver.model.Message;
import com.unlimited.appserver.model.User;
import com.unlimited.appserver.service.BookManager;
import com.unlimited.oj.Constants;
import com.unlimited.oj.dao.support.Page;
import com.unlimited.oj.model.LoginLog;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.util.ApplicationConfig;
import com.unlimited.oj.util.Tool;
import com.unlimited.oj.webapp.action.BaseAction;
import com.unlimited.oj.webapp.filter.URLDistributeFilter;
import com.unlimited.oj.webapp.filter.URLDistributeFilter;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


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
    
    /** APP传过来的用户名  */
    private String username;
    
    /** APP传过来的密码  */
    private String password;
    
    /** APP传过来的讨论标题  */
    private String discussTitle;
    
    /** APP传过来的讨论正文 */
    private String discussContent;
    
    /** APP传过来的讨论id */
    private String discussId;
    
    /** APP传过来的试卷id */
    private String examPaperId;
    
    /** APP传过来的回复的讨论id */
    private String replyId;
    
    /** APP传过来的书本id */
    private String bookId;
    
    /** OJ的基础地址 */
//    private String url = "http://172.26.14.60:8000/uoj/";
    private String url = "http://acm.scau.edu.cn:8000/uoj/";

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
		try {
			examPaperId = getRequest().getParameter("examPaperId");
        } catch (Exception e) { /*not need handle */ }
		try {
			replyId = getRequest().getParameter("replyId");
        } catch (Exception e) { /*not need handle */ }
		try {
			bookId = getRequest().getParameter("bookId");
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
//    	String hex = "1";
//    	try {
//			hex = (new HexBinaryAdapter()).marshal(MessageDigest.getInstance("MD5").digest("1".getBytes()));
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		returnString = hex;
    	returnString = "网络已连接";
    	System.out.println("接收到get请求");
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
    	String parameter = "?token=" + token + "&parentId=" + parentId;//需要的参数
    	try {
			log.debug(url + currentURL + parameter);
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
    		User user = new User(username, password, token);
    		if(userManager == null) log.debug("-------------------MANAGER NULL------------------");
    		userManager.saveUser(user);
    		returnString = "token";
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnString = "404";
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
    	System.out.println(username+password+"登陆");
    	if(userManager.isUserExist(username, password)) {
//    		Cookie cookie = new Cookie("USERID", "" + userId);
//    		getResponse().addCookie(cookie);
        	User user = userManager.getUserByUsername(username);
        	returnString = user.getToken();
    	}
    	else returnString = "404";
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
    
    /**
     * 
     * @Title: user_PostDiscuss_PUBLIC 
     * @Description: 发表新的讨论 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_PostDiscuss_PUBLIC() {
		boolean flag = userManager.isUserExist(username, password);
		if(flag == false) {
			returnString = "账号名或密码错误!";
		}
		else {
			Long userID = userManager.getUserIdByUsername(username);
			if(replyId == null || Long.parseLong(replyId) <= 0) {
				discussManager.postDiscuss(new Discuss(discussTitle, discussContent, Long.parseLong(problemId), userID));
			}
			else {
				discussManager.postDiscuss(new Discuss(discussTitle, discussContent, Long.parseLong(problemId), userID, Long.parseLong(replyId)));
				Long replyUserId = discussManager.getDiscussUserIDByDiscussID(Long.parseLong(replyId));
				messageManager.saveMessage(new Message(1, Long.parseLong(problemId), "你在题目" + Long.parseLong(problemId) + "的讨论有新的回复", replyUserId));
			}
			returnString = "发帖成功!";
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_removeDiscuss_PUBLIC 
     * @Description: 删除讨论 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_removeDiscuss_PUBLIC() {
    	discussManager.removeDiscuss(Long.parseLong(discussId));
		returnString = "删除成功!";
		return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_exam_examPaper_getAllOfUser_PUBLIC 
     * @Description: 通过用户token取得用户参加的所有考试 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_exam_examPaper_getAllOfUser_PUBLIC() {
    	String currentURL = "app_exam_examPaper_getAllOfUser.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token;//需要的参数
    	try {
    		System.out.println(url + currentURL + parameter);
			getResponse().sendRedirect(url + currentURL + parameter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_exam_examPaper_getDetailOfExamPaper 
     * @Description: 通过试卷编号获取考试详情 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_exam_examPaper_getDetailOfExamPaper_PUBLIC() {
    	String currentURL = "app_exam_examPaper_getDetailOfExamPaper.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token + "&examPaperId=" + examPaperId;//需要的参数
    	try {
    		System.out.println(url + currentURL + parameter);
			getResponse().sendRedirect(url + currentURL + parameter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_exam_examSolution_getListOfExamPaper_PUBLIC 
     * @Description: 通过试卷编号获取提交记录 
     * @return user_echo_PUBLIC.jsp
     */
    public String user_exam_examSolution_getListOfExamPaper_PUBLIC() {
    	String currentURL = "app_exam_examSolution_getListOfExamPaper.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token + "&examPaperId=" + examPaperId;//需要的参数
    	try {
    		System.out.println(url + currentURL + parameter);
			getResponse().sendRedirect(url + currentURL + parameter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    /**
     * 查找该用户的所有推送信息
     * @Title: user_GetMessageByUserId 
     * @Description: TODO 
     * @param @return
     * @return String
     * @throws
     */
    public String user_GetMessageByUserId_PUBLIC() {
    	returnString = "无消息";
    	try {
    		if(userManager.isUserExist(username, password)) {
        		Long userID = userManager.getUserIdByUsername(username);
        		returnString = messageManager.getMessageByUserID(userID);
    		}
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	return "returnAppData";
    }
    
    /** 
     * @Title: user_GetBookByBookId_PUBLIC 
     * @Description: 通过书本ID获取书本详细信息 
     * @param @return
     * @return String
     * @throws 
     */
    public String user_GetBookByBookId_PUBLIC() {
    	returnString = "获取失败";
    	try {
    		returnString = bookManager.getBookByBookID(bookId);
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	return "returnAppData";
    }
    
    /**
     * 
     * @Title: user_problem_getProblemRecommendation_PUBLIC 
     * @Description: 通过用户token获取题目推荐
     * @return user_echo_PUBLIC.jsp
     * @throws InterruptedException 
     */
    public String user_problem_getProblemRecommendation_PUBLIC() throws InterruptedException {
    	String currentURL = "app_common_solution_getAllAcceptedOfUser.html";//该方法对应OJ API的html
    	String parameter = "?token=" + token;//需要的参数
    	try {
    		HttpClient httpClient = new HttpClient();
    		GetMethod getMethod = new GetMethod(url + currentURL + parameter);
    		httpClient.executeMethod(getMethod);
    		String str = getMethod.getResponseBodyAsString();
    		returnString = "";
    		int cnt = 0;
    		for(int i = 0; i < str.length(); ) {
    			int start = str.indexOf("\"problemId\":", i);
    			int end = str.indexOf(",", start);
    			if(start < 0) break;
    			i = end;
    			returnString += str.substring(start + 12, end) + " ";
    			cnt++;
    		}
    		returnString = cnt + " " + returnString;
			String input_file = "C:\\recommendation\\recommendation_real\\bin\\Debug\\in.txt";
            File exeFile = new File("C:\\recommendation\\recommendation_real\\bin\\Debug\\recommendation.exe");
            File output_file = new File("C:\\recommendation\\recommendation_real\\bin\\Debug\\out.txt");
			File file = new File(input_file);
			BufferedWriter out = new BufferedWriter(new FileWriter(input_file));
			out.write(returnString);
            out.flush();
            out.close();
            String command = exeFile.toString() + " < " + input_file.toString();// + " > " + output_file.toString();
            Process process = Runtime.getRuntime().exec("cmd /c " + command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
            String line = null;  
            returnString = "";
            while ((line = br.readLine()) != null && !line.isEmpty()) {
            	returnString = line;
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "returnAppData";
    }
    
    public void user_RemoveMessageByMessageId() {
    	//TODO
    }
    
    public String getReturnString() {
		return returnString;
	}
}
