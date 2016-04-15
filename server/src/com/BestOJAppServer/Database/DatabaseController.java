package com.BestOJAppServer.Database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController {
	
	private String responseString;
	
	private Integer userId;
	
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
	
	public ResultSet query(String sqlString, int type) {
		Connection connection = getConnection();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			if(type == 1) {
				responseString = "{\"discussJavaBeans\":";
				
				boolean flag = false;

				try {
					while(resultSet.next()) {
						if(!flag) {
							responseString += "[";
							flag = true;
						}
						else responseString += ",";
						responseString += "{\"id\":";
						responseString += resultSet.getInt(1);
						responseString += ",\"title\":";
						responseString += "\"";
						responseString += resultSet.getString(2);
						responseString += "\"";
						responseString += ",\"content\":";
						responseString += "\"";
						responseString += resultSet.getString(3);
						responseString += "\"";
						responseString += ",\"postTime\":";
						responseString += "\"";
						responseString += resultSet.getString(4);
						responseString += "\"";
						responseString += ",\"problemId\":";
						responseString += resultSet.getLong(5);
						responseString += ",\"userID\":";
						responseString += resultSet.getInt(6);
						responseString += "}";
					}
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(flag) {
					responseString += "]";
					responseString += ",\"echo\":0}";
				}
				else {
					responseString += ",\"echo\":2}";
				}
			}
			else if(type == 2) {
				while(resultSet.next()) {
					userId = resultSet.getInt(1);
				}
			}
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

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
