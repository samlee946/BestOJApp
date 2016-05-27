package com.unlimited.appserver.dao;

import java.util.List;

import com.unlimited.appserver.model.Message;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

public interface MessageDAO extends GenericDao<Message, Long> {

	/**
	 * 查找该用户的所有推送信息
	 * @Title: getMessageByUserID 
	 * @Description: TODO 
	 * @param @param userID
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getMessageByUserID(Long userID);
	
	/**
	 * 通过编号查找单条推送信息
	 * @Title: getMessageByMessageID 
	 * @Description: TODO 
	 * @param @param MessageID
	 * @param @return
	 * @return Message
	 * @throws
	 */
	public Message getMessageByMessageID(Long messageID);
}
