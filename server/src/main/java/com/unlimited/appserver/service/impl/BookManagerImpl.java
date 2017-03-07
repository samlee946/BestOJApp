package com.unlimited.appserver.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;

import com.unlimited.appserver.dao.BookDao;
import com.unlimited.appserver.dao.exception.BookNotFoundException;
import com.unlimited.appserver.dao.exception.DiscussNotFoundException;
import com.unlimited.appserver.model.Book;
import com.unlimited.appserver.service.BookManager;
import com.unlimited.oj.service.impl.GenericManagerImpl;

public class BookManagerImpl extends GenericManagerImpl<Book, Long> implements BookManager {
	
	private BookDao bookDao;
	
	@Override
	public String getBookByBookID(String bookID) throws BookNotFoundException {
		String ret = "获取失败";
		try {
			ret = bookDao.getBookByBookID(bookID).toString();
			log.debug(ret);
			return ret;
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new BookNotFoundException("bookID '" + bookID + "' Not Found!");
		}
	}

	@Override
	public String getBookList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the bookDao
	 */
	public BookDao getBookDao() {
		return bookDao;
	}

	/**
	 * @param bookDao the bookDao to set
	 */
	@Required
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	
}
