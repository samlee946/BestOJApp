package com.BestOJAppServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BestOJAppServer.JavaBean.UserManager;

@WebServlet(name = "registerServlet", urlPatterns = {"/register.htm"})
public class RegisterServlet extends HttpServlet {

	//序列化
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("RegisterServlet Init()");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		
		String userNameString = request.getParameter("username");		//获取用户名
		String passwdString = request.getParameter("passwd");			//获取密码
		
		PrintWriter printWriter = null;
		
		UserManager userManager = new UserManager();
		
		if(!userManager.isUserNameValid(userNameString)) {
			System.out.println("用户名无效或已存在!");
		} else if(!userManager.isPasswdValid(passwdString)) {
			System.out.println("密码无效!");
		} else {
			if(userManager.createUser(userNameString, passwdString)) {
				printWriter = response.getWriter();
				printWriter.println("注册成功!");
			} else {
				printWriter = response.getWriter();
				printWriter.println("注册失败!");
			}
		}
	}
}
