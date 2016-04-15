package com.BestOJAppServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BestOJAppServer.JavaBean.DiscussManager;
import com.BestOJAppServer.JavaBean.UserManager;

public class PostDiscuss extends HttpServlet {

	//序列化
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		
		Cookie[] cookies = request.getCookies();
		
		String responseString = "";
		
		String userId = "";
		
		boolean flag = false;
		
		UserManager userManager = new UserManager();

		DiscussManager discussManager = new DiscussManager();
		
		PrintWriter printWriter = response.getWriter();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("USERID") && !cookie.getValue().equals("null")) {
					userId = cookie.getValue();
					System.out.println(userId);
					if(userManager.isUserExist(userId) == true) {
						flag = true;
					}
					break;
				}
			}
		}
		if(flag == false) {
			responseString = "请先登录!";
		}
		else {
			String titleString = request.getParameter("title");
			String contentString = request.getParameter("content");
			String problemId = request.getParameter("problemId");
			boolean result = discussManager.postDiscuss(userId, problemId, titleString, contentString);
			if(result == true) responseString = "发表讨论成功!";
			else responseString = "发表讨论失败!";
			//System.out.println(titleString + contentString + problemId + userId);
		}
		printWriter.write(responseString);
	}
}
