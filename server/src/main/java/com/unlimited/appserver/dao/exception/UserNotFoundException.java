package com.unlimited.appserver.dao.exception;

/**
 * 
 * @ClassName: UserNotFoundException 
 * @Description: 用于抛出用户未找到的异常
 * @author Sam Lee
 * @date 2016-4-29 上午05:51:21 
 * @email samlee946@gmail.com
 *
 */
public class UserNotFoundException extends Exception {
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
