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
@Entity(name="discuss")
@Table(name="discuss")
public class Discuss {
	/** 唯一ID */
	private Long id;
	/** 讨论的标题 */
	private String discussTitle;
	/** 讨论的内容 */
	private String discussContent;
	/** 发表讨论的时间 */
	private String discussDate;
	/** 表示讨论是关于哪道题目的 */
	private Long problemID;
	/** 发帖用户 */
	private Long userID;
	
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
	 * @return the discussTitle
	 */
	@Basic(optional=true)
	@Column(name="discussTitle", nullable=true, insertable=true, updatable=true, length=65535)
	public String getDiscussTitle() {
		return discussTitle;
	}
	/**
	 * @param discussTitle the discussTitle to set
	 */
	public void setDiscussTitle(String discussTitle) {
		this.discussTitle = discussTitle;
	}
	/**
	 * @return the discussContent
	 */
	@Basic(optional=true)
	@Column(name="discussContent", nullable=true, insertable=true, updatable=true, length=65535)
	public String getDiscussContent() {
		return discussContent;
	}
	/**
	 * @param discussContent the discussContent to set
	 */
	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}
	/**
	 * @return the discussDate
	 */
	@Basic(optional=true)
	@Column(name="discussDate", nullable=true, insertable=true, updatable=true, length=256)
	public String getDiscussDate() {
		return discussDate;
	}
	/**
	 * @param discussDate the discussDate to set
	 */
	public void setDiscussDate(String discussDate) {
		this.discussDate = discussDate;
	}
	/**
	 * @return the problemID
	 */
	@Basic(optional=false)
	@Column(name="problemID", nullable=false, insertable=true, updatable=true)
	public Long getProblemID() {
		return problemID;
	}
	/**
	 * @param problemID the problemID to set
	 */
	public void setProblemID(Long problemID) {
		this.problemID = problemID;
	}
	/**
	 * @return the userID
	 */
	@Basic(optional=false)
	@Column(name="userID", nullable=false, insertable=true, updatable=true)
	public Long getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	/**  */
	public Discuss() {
		super();
	}
	/**  */
	public Discuss(String discussTitle, String discussContent, Long problemID,
			Long userID) {
		super();
		this.discussTitle = discussTitle;
		this.discussContent = discussContent;
		this.problemID = problemID;
		this.userID = userID;
		this.discussDate = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"id\":" + id + ",\"title\":\"" + discussTitle
				+ "\",\"content\":\"" + discussContent + "\",\"postTime\":\""
				+ discussDate + "\",\"problemId\":" + problemID + ",\"userID\":"
				+ userID + "}";
	}
}
