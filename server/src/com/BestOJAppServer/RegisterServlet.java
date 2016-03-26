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

import org.omg.CORBA.PRIVATE_MEMBER;

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
		
		response.setContentType("application/json;charset=utf-8");
		
		String userNameString = request.getParameter("username");		//获取用户名
		String passwdString = request.getParameter("passwd");			//获取密码
		
		List<String> errors = new ArrayList<String>(); 					//装载错误信息
		
		PrintWriter printWriter = null;
		
		if(!isValidUserName(userNameString)) {
			errors.add("用户名无效或已存在!");
			System.out.println("用户名无效或已存在!");
		}
		if(!isValidPasswd(passwdString)) {
			errors.add("密码无效!");
			System.out.println("密码无效!");
		}
		if(!errors.isEmpty()) {
			//response.setHeader("success", "no");
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
					try {
						printWriter = response.getWriter();
						printWriter.println("注册成功!");
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						printWriter.close();
					}
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
		int cnt = 0;
		if(userNameString == null || "".equals(userNameString) || userNameString.length() > 16) {
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
				resultSet.last();
				cnt = resultSet.getRow();
				resultSet.beforeFirst();
				if(cnt != 0) {
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
		if(passwdString == null || "".equals(passwdString) || passwdString.length() < 6 || passwdString.length() > 16) {
			flag = false;
		}
		return flag;
	}
}
