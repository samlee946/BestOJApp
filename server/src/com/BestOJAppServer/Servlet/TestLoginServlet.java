package com.BestOJAppServer.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestLoginServlet extends HttpServlet {

	//ÐòÁÐ»¯
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		System.out.println("²âÊÔµÇÂ½: requset:" + request.getSession().getId());
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				System.out.println("cookie:" + cookie.getName() + " " + cookie.getValue());
				if(cookie.getName().equals("JSESSIONID") && !cookie.getValue().equals("null")) {
					flag = true;
					System.out.println("µÇÂ½³É¹¦À±!");
					break;
				}
			}
		}
		if(flag == false) {
			System.out.println("µÇÂ½Ê§°Ü!");
		}
		System.out.println("²âÊÔµÇÂ½Íê±Ï!");
	}
}
