package com.unlimited.appserver.service;

import com.unlimited.appserver.dao.exception.DiscussNotFoundException;
import com.unlimited.appserver.model.Discuss;

public interface DiscussManager {

	public void saveDiscuss(Discuss discuss) throws DiscussNotFoundException;
	
	public String getDiscussByProblemID(Long problemID) throws DiscussNotFoundException;
	
	public boolean postDiscuss(Discuss discuss);
	
	public void removeDiscuss(Long discussId);
	
	/**
	 * 通过讨论编号获取发讨论的用户的编号
	 * @Title: getDiscussUserIDByDiscussID 
	 * @Description: TODO 
	 * @param @param discussId
	 * @param @return
	 * @return Long
	 * @throws
	 */
	public Long getDiscussUserIDByDiscussID(Long discussId);
}
