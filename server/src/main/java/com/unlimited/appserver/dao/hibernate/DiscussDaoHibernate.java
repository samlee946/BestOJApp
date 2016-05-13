package com.unlimited.appserver.dao.hibernate;

import java.util.List;

import com.unlimited.appserver.dao.IDiscussDAO;
import com.unlimited.appserver.model.Discuss;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

public class DiscussDaoHibernate extends GenericDaoHibernate<Discuss, Long> implements IDiscussDAO{

	public DiscussDaoHibernate() {
		super(Discuss.class);
		// TODO Auto-generated constructor stub
	}

	public String getDiscussByProblemID(Long problemID) {
		String ret = "{\"discusses\":";
		List discusses = getHibernateTemplate().find("from discuss where problemID = " + problemID);
		ret += "[";
		for(int i = 0; i < discusses.size(); i++) {
			if(i == 0) {
			}
			else {
				ret += ",";
			}
			ret += ((Discuss) discusses.get(i)).toString();
		}
		ret += "]";
		if(discusses.size() > 0) {
			ret += ",\"echo\":0}";
		}
		else {
			ret += ",\"echo\":2}";
		}
		return ret;
	}
	
	public Discuss getDiscussByDiscussID(Long DiscussID) {
		List discusses = getHibernateTemplate().find("from discuss where id = " + DiscussID);
		if(discusses.size() == 1) {
			return (Discuss) discusses.get(0);
		}
		return null;
	}
}
