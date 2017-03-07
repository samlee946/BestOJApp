package com.unlimited.appserver.dao.exception;

/** 
 * 用于抛出书本没找到的异常
 * @ClassName: BookNotFoundException 
 * @Description: TODO
 * @author Sam Lee
 * @date Mar 6, 2017 3:59:59 PM 
 * @email samlee946@gmail.com
 *  
 */
public class BookNotFoundException extends Exception {
	public BookNotFoundException(String msg) {
		super(msg);
	}
}
