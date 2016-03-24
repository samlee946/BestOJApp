package com.BestOJAppServer;

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
		String userNameString = request.getParameter("username");		//获取用户名
		String passwdString = request.getParameter("passwd");			//获取密码
		
		List<String> errors = new ArrayList<String>(); 					//装载错误信息
		if(!isValidUserName(userNameString)) {
			errors.add("用户名无效或已存在!");
			System.out.println("用户名无效或已存在!");
		}
		if(!isValidPasswd(passwdString)) {
			errors.add("密码无效!");
			System.out.println("密码无效!");
		}
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("success", "no");
			//request.getRequestDispatcher("/error.htm").forward(request, response);
		} else {
			Connection connection = null;
			Statement statement = null;
			int result = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestojappserver", "root", "123456mysql");
				statement = connection.createStatement();
				
				String sqlStrig = "insert into `User`(`userName`, `password`) values('" + userNameString + "', '" + passwdString + "');";
				
				result = statement.executeUpdate(sqlStrig);
				if(result == 1) {
					request.setAttribute("success", "yes");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean isValidUserName(String userNameString) {
		boolean flag = true;
		if(userNameString == null || "".equals(userNameString)) {
			flag = false;
		} else {
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestojappserver", "root", "123456mysql");
				statement = connection.createStatement();

				String sqlStrig = "select * from `User` where `userName` = '" + userNameString + "';";
				System.out.println(sqlStrig);
				resultSet = statement.executeQuery(sqlStrig);
				
				if(resultSet.getRow() != 0) {
					flag = false;
				}
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
		return flag;
	}
	
	public boolean isValidPasswd(String passwdString) {
		boolean flag = true;
		if(passwdString == null || "".equals(passwdString) || passwdString.length() < 6) {
			flag = false;
		}
		return flag;
	}
}
