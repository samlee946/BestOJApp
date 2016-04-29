package com.unlimited.appserver.dao.exception;

/**
 * 
 * @ClassName: UserExistsException 
 * @Description: 用于抛出用户已经存在的异常
 * @author Sam Lee
 * @date 2016-4-29 上午09:48:40 
 * @email samlee946@gmail.com
 *
 */
public class UserExistsException extends Exception {

	public UserExistsException(String msg) {
		super(msg);
	}
}
