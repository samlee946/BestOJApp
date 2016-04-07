package com.BestOJAppServer.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListOfUserByProblemId extends HttpServlet {
	
	String url = "http://acm.scau.edu.cn:8000/uoj/app_common_solution_getListOfUserByProblemId.html";
	String token = null;
	String problemId;
	
	//���л�
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("GetListOfUserByProblemId Init()");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");

		token = request.getParameter("token");
		problemId = request.getParameter("problemId");
		String newurl = url + "?token=" + token + "&problemId=" + problemId;
		System.out.println(token + "_" + problemId + "_" + url);
		response.sendRedirect(newurl);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
