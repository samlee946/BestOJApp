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

	//���л�
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("RegisterServlet Init()");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		
		String userNameString = request.getParameter("username");		//��ȡ�û���
		String passwdString = request.getParameter("passwd");			//��ȡ����
		
		PrintWriter printWriter = null;
		
		UserManager userManager = new UserManager();
		
		if(!userManager.isUserNameValid(userNameString)) {
			System.out.println("�û�����Ч���Ѵ���!");
		} else if(!userManager.isPasswdValid(passwdString)) {
			System.out.println("������Ч!");
		} else {
			if(userManager.createUser(userNameString, passwdString)) {
				printWriter = response.getWriter();
				printWriter.println("ע��ɹ�!");
			} else {
				printWriter = response.getWriter();
				printWriter.println("ע��ʧ��!");
			}
		}
	}
}
