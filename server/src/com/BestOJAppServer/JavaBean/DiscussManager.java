package com.BestOJAppServer.JavaBean;

import java.sql.ResultSet;

import com.BestOJAppServer.Database.DatabaseController;

public class DiscussManager {
	DatabaseController databaseController = new DatabaseController();
	ResultSet resultSet;
	
	
	public String getDiscussByProblemID(String problemId) {
		String sqlString = "select * from `Discuss` where `problemID` = " + problemId;
		databaseController.query(sqlString, 1);
		return databaseController.getResponseString();
	}
	
	public boolean postDiscuss(String userId, String problemId, String title, String content) {
		String sqlString = "insert into `Discuss`(`discussTitle`, `discussContent`, `discussDate`, `problemID`, `userID`) values('" + title + "', '" + content + "', now(), '" + problemId + "', '" + userId + "'); ";
		int result = databaseController.update(sqlString);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	
}
