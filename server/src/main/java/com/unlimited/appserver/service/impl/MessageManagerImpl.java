package com.unlimited.appserver.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;

import com.unlimited.appserver.dao.MessageDAO;
import com.unlimited.appserver.model.Message;
import com.unlimited.appserver.service.MessageManager;
import com.unlimited.oj.service.impl.GenericManagerImpl;

public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager{

	private MessageDAO messageDAO;
	
	public void saveMessage(Message message) {
		try {
			messageDAO.save(message);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			log.warn(e.getMessage());
		}
	}
	
	public String getMessageByMessageId(Long MessageID) {
		return messageDAO.getMessageByMessageID(MessageID).toString();
	}
	
	public String getMessageByUserID(Long userID) {
		String ret = "获取失败";
		try {
			ret = messageDAO.getMessageByUserID(userID);
			return ret;
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
		}
		return ret;
	}
	
	public void removeMessge(Long messageId) {
		messageDAO.remove(messageDAO.getMessageByMessageID(messageId));
	}

	/**
	 * @return the messageDAO
	 */
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	/**
	 * @param messageDAO the messageDAO to set
	 */
	@Required
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	
}
