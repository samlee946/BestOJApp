package com.BestOJAppServer.Servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetOffspringByParentId extends HttpServlet {
	
	String url = "http://acm.scau.edu.cn:8000/uoj/app_common_ojTreeNode_getOffspringByParentId.html";
	//String url = "http://acm.scau.edu.cn:8000/uoj/app_common_ojTreeNode_getOffspringByParentId.html";
	String token = null;
	String parentId;
	
	//–Ú¡–ªØ
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("Init()");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");

		token = request.getParameter("token");
		parentId = request.getParameter("parentId");
		String newurl = url + "?token=" + token + "&parentId=" + parentId;
		System.out.println(token + "_" + parentId + "_" + url);
		response.sendRedirect(newurl);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
