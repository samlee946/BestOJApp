package com.BestOJAppServer.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PRIVATE_MEMBER;

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
		
		UserManager userManager = new UserManager();
		
		HttpSession session = request.getSession();
		
		String userNameString = request.getParameter("username");		//获取用户名
		String passwdString = request.getParameter("passwd");
		String JSESSIONID = session.getId();
		if(userManager.isUserExist(userNameString, passwdString)) {
			Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
			response.addCookie(cookie);
		}
	}
}
