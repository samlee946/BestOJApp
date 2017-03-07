package com.unlimited.appserver.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.naming.java.javaURLContextFactory;

/**
 * 
 * @ClassName: Discuss 
 * @Description: 讨论model
 * @author Sam Lee
 * @date 2016-4-29 上午09:23:45 
 * @email samlee946@gmail.com
 *
 */
@Entity(name="book")
@Table(name="book")
public class Book {
	/** 唯一ID */
	private Long id;
	/** 书名 */
	private String title;
	/** 作者 */
	private String author;
	/** 书本的简介 */
	private String intro;
	/** 书本的目录 */
	private String content;
	/** 发表讨论的时间 */
	private Float rating;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, insertable=true, updatable=true)
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	@Basic(optional=true)
	@Column(name="title", nullable=true, insertable=true, updatable=true, length=128)
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	@Basic(optional=true)
	@Column(name="author", nullable=true, insertable=true, updatable=true, length=128)
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the intro
	 */
	@Basic(optional=true)
	@Column(name="intro", nullable=true, insertable=true, updatable=true, length=65535)
	public String getIntro() {
		return intro;
	}
	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	/**
	 * @return the content
	 */
	@Basic(optional=true)
	@Column(name="content", nullable=true, insertable=true, updatable=true, length=65535)
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the rating
	 */
	@Basic(optional=true)
	@Column(name="rating", nullable=true, insertable=true, updatable=true, length=32)
	public Float getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	/**  */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**  */
	public Book(Long id, String title, String author, String intro,
			String content, Float rating) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.intro = intro;
		this.content = content;
		this.rating = rating;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"id\":" + id + ",\"title\":\"" + title
				+ "\",\"author\":\"" + author + "\",\"intro\":\"" + intro
				+ "\",\"content\":\"" + content + "\",\"rating\":"
				+ rating + "}";
	}
}
