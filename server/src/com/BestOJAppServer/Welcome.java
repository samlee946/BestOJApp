package com.BestOJAppServer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Welcome extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<html>");
		printWriter.println("<head><title>Welcome!</title>");
		printWriter.println("<body>");
		printWriter.println("Welcome to BestOJ App Server!");
		printWriter.println("</body>");
		printWriter.println("</html>");
		printWriter.flush();
		printWriter.close();
	}
}
