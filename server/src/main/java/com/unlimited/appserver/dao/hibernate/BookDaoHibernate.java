package com.unlimited.appserver.dao.hibernate;

import java.util.List;

import com.unlimited.appserver.dao.BookDao;
import com.unlimited.appserver.dao.exception.BookNotFoundException;
import com.unlimited.appserver.model.Book;
import com.unlimited.appserver.model.Discuss;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

public class BookDaoHibernate extends GenericDaoHibernate<Book, Long> implements BookDao{

	public BookDaoHibernate() {
		super(Book.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book getBookByBookID(String bookID) throws BookNotFoundException {
		List books = getHibernateTemplate().find("from book where id = " + bookID);
		if(books == null || books.isEmpty()) {
			throw new BookNotFoundException("bookID '" + bookID + "' Not Found!");
		}
		else {
			return (Book) books.get(0);
		}
	}

	@Override
	public String getBookList() {
		//TODO
		return null;
	}

}
