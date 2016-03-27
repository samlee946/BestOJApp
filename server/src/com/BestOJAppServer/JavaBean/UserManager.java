package com.BestOJAppServer.JavaBean;
import com.BestOJAppServer.Database.*;

public class UserManager {
	DatabaseController databaseController = new DatabaseController();
	public boolean isUserNameValid(String userNameString) {
		boolean flag = true;
		int cnt = 0;
		if(userNameString == null || "".equals(userNameString) || userNameString.length() > 16) {
			flag = false;
		} else {
			String sqlString = "select * from `User` where `userName` = '" + userNameString + "';";
			cnt = databaseController.queryCount(sqlString);
			if(cnt != 0) {
				flag = false;
			}
		}
		return flag;
	}
	
	public boolean isPasswdValid(String passwdString) {
		boolean flag = true;
		if(passwdString == null || "".equals(passwdString) || passwdString.length() < 6 || passwdString.length() > 16) {
			flag = false;
		}
		return flag;
	}
	
	public boolean isUserExist(String userNameString, String passwdString) {
		boolean flag = false;
		int cnt = 0;
		String sqlString = "select * from `User` where `userName` = '" + userNameString + "' and `password` = '" + passwdString + "';";
		cnt = databaseController.queryCount(sqlString);
		if(cnt == 1) {
			flag = true;
		}
		return flag;
	}
	
	public boolean createUser(String userNameString, String passwdString) {
		boolean flag = false;
		int result = 0;
		if(isUserNameValid(userNameString) && isPasswdValid(passwdString)) {
			String sqlString = "insert into `User`(`userName`, `password`) values('" + userNameString + "', '" + passwdString + "');";
			result = databaseController.update(sqlString);
			if(result == 1) {
				flag = true;
			}
		}
		return flag;
	}
}
