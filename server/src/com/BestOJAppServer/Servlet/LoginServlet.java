package com.BestOJAppServer.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BestOJAppServer.JavaBean.UserManager;

public class LoginServlet extends HttpServlet {

	//序列化
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("Init()");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		
		UserManager userManager = new UserManager();
		
		HttpSession session = request.getSession();
		
		String userNameString = request.getParameter("username");		//获取用户名
		String passwordString = request.getParameter("password");
		String JSESSIONID = session.getId();
		if(userManager.isUserExist(userNameString, passwordString)) {
			Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
			response.addCookie(cookie);
			response.getWriter().print("登陆成功!");
			System.out.println(userNameString + "已登陆");
		}
	}
}
