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

	//���л�
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
		
		String userNameString = request.getParameter("username");		//��ȡ�û���
		String passwordString = request.getParameter("password");
		String JSESSIONID = session.getId();
		if(userManager.isUserExist(userNameString, passwordString)) {
			Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
			response.addCookie(cookie);
			response.getWriter().print("��½�ɹ�!");
			System.out.println(userNameString + "�ѵ�½");
		}
	}
}
