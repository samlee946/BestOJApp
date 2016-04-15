package com.BestOJAppServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BestOJAppServer.JavaBean.DiscussManager;

public class GetDiscussByProblemId extends HttpServlet {

	//–Ú¡–ªØ
	private static final long serialVersionUID = 1L;
	
	private DiscussManager discussManager;
	
	private ResultSet resultSet;
	
	private String problemId;
	
	private PrintWriter printWriter;

	public void init() throws ServletException {
		System.out.println("GetDiscussByProblemId Init()");
		discussManager = new DiscussManager();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
		
		problemId = request.getParameter("problemId");
		
		discussManager.getDiscussByProblemID(problemId);
		
		resultSet = discussManager.getResultSet();
		
		printWriter = response.getWriter();
		
		String responseString = discussManager.getDiscussByProblemID(problemId);
		
		printWriter.write(responseString);
		
		System.out.println(responseString);
	}
}
