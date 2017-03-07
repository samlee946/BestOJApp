package com.unlimited.appserver.dao;

import com.unlimited.appserver.dao.exception.BookNotFoundException;
import com.unlimited.appserver.model.Book;

public interface BookDao {
	
	/** 
	 * 根据图书编号获取图书详细信息
	 * @Title: getBookByBookID 
	 * @Description: TODO 
	 * @param @param bookID
	 * @param @return
	 * @return Book
	 * @throws 
	 */
	public Book getBookByBookID(String bookID) throws BookNotFoundException;
	
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
