package com.unlimited.appserver.service;

import com.unlimited.appserver.dao.exception.BookNotFoundException;

public interface BookManager {
	
	/** 
	 * 根据图书编号获取图书详细信息
	 * @Title: getBookByBookID 
	 * @Description: TODO 
	 * @param @param bookID
	 * @param @return
	 * @return String
	 * @throws 
	 */
	public String getBookByBookID(String bookId) throws BookNotFoundException;
	
	/**
	 * 获取书本列表
	 * @Title: getBookList 
	 * @Description: TODO 
	 * @param 
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getBookList();
}
