package com.BestOJAppServer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckNetwork extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print("ÍøÂçÒÑÁ¬½Ó");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			printWriter.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
