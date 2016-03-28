package com.BestOJAppServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		String passwordString = request.getParameter("password");			//获取密码
		
		PrintWriter printWriter = response.getWriter();
		
		UserManager userManager = new UserManager();
		
		if(!userManager.isUserNameValid(userNameString)) {
			System.out.println("用户名无效或已存在!");
			printWriter.print("用户名无效或已存在!");
		} else if(!userManager.ispasswordValid(passwordString)) {
			System.out.println("密码无效!");
			printWriter.print("密码无效!");
		} else {
			if(userManager.createUser(userNameString, passwordString)) {
				printWriter.print("注册成功!");
			} else {
				printWriter.print("注册失败!");
			}
		}
	}
}
