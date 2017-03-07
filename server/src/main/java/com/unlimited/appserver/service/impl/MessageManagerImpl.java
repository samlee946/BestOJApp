package com.unlimited.appserver.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;

import com.unlimited.appserver.dao.MessageDao;
import com.unlimited.appserver.model.Message;
import com.unlimited.appserver.service.MessageManager;
import com.unlimited.oj.service.impl.GenericManagerImpl;

public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager{

	private MessageDao messageDao;
	
	public void saveMessage(Message message) {
		try {
			messageDao.save(message);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			log.warn(e.getMessage());
		}
	}
	
	public String getMessageByMessageId(Long MessageID) {
		return messageDao.getMessageByMessageID(MessageID).toString();
	}
	
	public String getMessageByUserID(Long userID) {
		String ret = "获取失败";
		try {
			ret = messageDao.getMessageByUserID(userID);
			return ret;
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
		}
		return ret;
	}
	
	public void removeMessge(Long messageId) {
		messageDao.remove(messageDao.getMessageByMessageID(messageId));
	}

	/**
	 * @return the messageDao
	 */
	public MessageDao getMessageDao() {
		return messageDao;
	}

	/**
	 * @param messageDao the messageDao to set
	 */
	@Required
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	
}
