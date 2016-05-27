package com.unlimited.appserver.service;

import org.springframework.dao.DataIntegrityViolationException;

import com.unlimited.appserver.model.Message;

public interface MessageManager {

	/**
	 * 保存一个message
	 * @Title: saveMessage 
	 * @Description: TODO 
	 * @param @param message
	 * @return void
	 * @throws
	 */
	public void saveMessage(Message message);
	
	/**
	 * 查找该用户的所有推送信息
	 * @Title: getMessageByUserID 
	 * @Description: TODO 
	 * @param @param UserID
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getMessageByUserID(Long UserID);
	
	/** 
	 * 通过messageId删除推送信息(已读信息)
	 * @Title: removeMessge 
	 * @Description: TODO 
	 * @param @param messageId
	 * @return void
	 * @throws
	 */
	public void removeMessge(Long messageId);
	
	public String getMessageByMessageId(Long MessageID);
}
