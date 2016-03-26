package com.BestOJAppServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PRIVATE_MEMBER;

public class LoginServlet extends HttpServlet {

	//序列化
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("Init()");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String userNameString = request.getParameter("username");		//获取用户名
		String passwdString = request.getParameter("passwd");
		String JSESSIONID = session.getId();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		int cnt;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestojappserver", "root", "123456mysql");
			statement = connection.createStatement();

			String sqlStrig = "select * from `User` where `userName` = '" + userNameString + "' and `password` = '" + passwdString + "';";
			System.out.println(sqlStrig);
			resultSet = statement.executeQuery(sqlStrig);
			resultSet.last();
			cnt = resultSet.getRow();
			resultSet.beforeFirst();
			if(cnt != 0) {
				Cookie cookie = new Cookie("JSESSIONID", JSESSIONID);
				response.addCookie(cookie);
			}
			//else response.addCookie(null);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
