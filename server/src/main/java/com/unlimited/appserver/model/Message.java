package com.unlimited.appserver.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: Message 
 * @Description: 推送信息的Model
 * @author Sam Lee
 * @date 2016-5-27 上午03:10:28 
 * @email samlee946@gmail.com
 *
 */
@Entity(name="messages")
@Table(name="messages")
public class Message {
	
	/** 唯一id */
	private Long id;
	
	/** 推送的时间 */
	private String date;
	
	/** 推送的类型 */
	private Integer type;
	
	/** 与该推送有关的id */
	private Long linkedId;
	
	/** 推送的附加信息 */
	private String extraMsg;
	
	/** 与该推送有关的用户id */
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
	 * @return the date
	 */
	@Basic(optional=true)
	@Column(name="date", nullable=true, insertable=true, updatable=true, length=20)
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	

	/**
	 * @return the type
	 */
	@Basic(optional=true)
	@Column(name="type", nullable=true, insertable=true, updatable=true, length=11)
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the linkedId
	 */
	@Basic(optional=true)
	@Column(name="linkedID", nullable=true, insertable=true, updatable=true, length=20)
	public Long getLinkedId() {
		return linkedId;
	}

	/**
	 * @param linkedId the linkedId to set
	 */
	public void setLinkedId(Long linkedId) {
		this.linkedId = linkedId;
	}

	/**
	 * @return the extraMsg
	 */
	@Basic(optional=true)
	@Column(name="extraMsg", nullable=true, insertable=true, updatable=true, length=65535)
	public String getExtraMsg() {
		return extraMsg;
	}

	/**
	 * @param extraMsg the extraMsg to set
	 */
	public void setExtraMsg(String extraMsg) {
		this.extraMsg = extraMsg;
	}

	/**
	 * @return the userID
	 */
	@Basic(optional=true)
	@Column(name="userID", nullable=true, insertable=true, updatable=true, length=20)
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
	public Message(Long id) {
		super();
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"id\":" + id + ",\"date\":\"" + date + "\",\"type\":" + type
				+ ",\"linkedId\":" + linkedId + ",\"extraMsg\":\"" + extraMsg
				+ "\",\"userID\":" + userID
				+ "}";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((extraMsg == null) ? 0 : extraMsg.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((linkedId == null) ? 0 : linkedId.hashCode());
//		result = prime * result + type;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (extraMsg == null) {
			if (other.extraMsg != null)
				return false;
		} else if (!extraMsg.equals(other.extraMsg))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (linkedId == null) {
			if (other.linkedId != null)
				return false;
		} else if (!linkedId.equals(other.linkedId))
			return false;
		if (type != other.type)
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}
	

	/**  */
	public Message(Long id, String date, int type, Long linkedId,
			String extraMsg, Long userID) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.linkedId = linkedId;
		this.extraMsg = extraMsg;
		this.userID = userID;
	}
	
	

	/**  */
	public Message(Integer type, Long linkedId, String extraMsg,
			Long userID) {
		super();
		this.date = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
		this.type = type;
		this.linkedId = linkedId;
		this.extraMsg = extraMsg;
		this.userID = userID;
	}

	/**  */
	public Message() {
		super();
	}
	
}
