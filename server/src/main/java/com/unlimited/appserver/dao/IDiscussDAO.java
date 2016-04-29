package com.unlimited.appserver.dao;

import com.unlimited.appserver.model.Discuss;
import com.unlimited.oj.dao.GenericDao;

public interface IDiscussDAO extends GenericDao<Discuss, Long>{

	public String getDiscussByProblemID(Long problemID);
	
	public Discuss getDiscussByDiscussID(Long DiscussID);
}
