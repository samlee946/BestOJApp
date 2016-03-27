package com.BestOJAppServer.Database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController {
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestojappserver", "root", "123456mysql");
		} catch (ClassNotFoundException e) {
			System.out.println("MYSQL驱动加载错误!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("MYSQL连接错误!");
			e.printStackTrace();
		}
		return connection;
	}
	
	public ResultSet query(String sqlString) {
		Connection connection = getConnection();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
		} catch (Exception e) {
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
		return resultSet;
	}
	
	public int queryCount(String sqlString) {
		Connection connection = getConnection();
		ResultSet resultSet = null;
		Statement statement = null;
		int cnt = 0;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			resultSet.last();
			cnt = resultSet.getRow();
			resultSet.beforeFirst();
		} catch (Exception e) {
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
		return cnt;
	}
	
	public int update(String sqlString) {
		Connection connection = getConnection();
		Statement statement = null;
		int result = 0;
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			result 	  = statement.executeUpdate(sqlString);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
