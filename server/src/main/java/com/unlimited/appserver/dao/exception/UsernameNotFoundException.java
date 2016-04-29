package com.unlimited.appserver.dao.exception;

/**
 * 
 * @ClassName: UsernameNotFoundException 
 * @Description: 用于抛出用户名未找到的异常
 * @author Sam Lee
 * @date 2016-4-29 上午05:49:02 
 * @email samlee946@gmail.com
 *
 */
public class UsernameNotFoundException extends Exception{
	
	public UsernameNotFoundException(String msg) {
		super(msg);
	}
}
