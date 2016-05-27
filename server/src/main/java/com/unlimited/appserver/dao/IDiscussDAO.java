package com.unlimited.appserver.dao;

import com.unlimited.appserver.model.Discuss;
import com.unlimited.oj.dao.GenericDao;

public interface IDiscussDAO extends GenericDao<Discuss, Long>{

	public String getDiscussByProblemID(Long problemID);
	
	public Discuss getDiscussByDiscussID(Long DiscussID);
	
	/**
	 * 通过讨论编号获取发讨论的用户的编号
	 * @Title: getDiscussUserIDByDiscussID 
	 * @Description: TODO 
	 * @param @param discussID
	 * @param @return
	 * @return Long
	 * @throws
	 */
	public Long getDiscussUserIDByDiscussID(Long discussID);
}
