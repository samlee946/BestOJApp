package com.unlimited.appserver.dao.exception;

/**
 * 
 * @ClassName: DiscussNotFoundException 
 * @Description: 用于抛出未找到讨论的异常
 * @author Sam Lee
 * @date 2016-4-29 上午09:46:00 
 * @email samlee946@gmail.com
 *
 */
public class DiscussNotFoundException extends Exception {
	
	public DiscussNotFoundException(String msg) {
		super(msg);
	}
}
