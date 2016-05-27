package com.unlimited.appserver.dao.hibernate;

import java.util.List;

import com.unlimited.appserver.dao.MessageDAO;
import com.unlimited.appserver.model.Discuss;
import com.unlimited.appserver.model.Message;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

public class MessageDaoHibernate extends GenericDaoHibernate<Message, Long> implements MessageDAO{

	public MessageDaoHibernate() {
		super(Message.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessageByUserID(Long userID) {
		// TODO Auto-generated method stub
		List<Message> messages = getHibernateTemplate().find("from messages where userID = " + userID);
		String ret = "[";
		for(int i = 0; i < messages.size(); i++) {
			if(i == 0) {
			}
			else {
				ret += ",";
			}
			ret += ((Message) messages.get(i)).toString();
		}
		ret += "]";
		log.error(ret);
		return ret;
	}

	@Override
	public Message getMessageByMessageID(Long messageID) {
		// TODO Auto-generated method stub
		List<Message> messages = getHibernateTemplate().find("from messages where id = " + messageID);
		return (Message) messages.get(0);
	}
}
