package com.unlimited.appserver.service;

import com.unlimited.appserver.dao.exception.DiscussNotFoundException;
import com.unlimited.appserver.model.Discuss;

public interface DiscussManager {

	public void saveDiscuss(Discuss discuss) throws DiscussNotFoundException;
	
	public String getDiscussByProblemID(Long problemID) throws DiscussNotFoundException;
	
	public boolean postDiscuss(Discuss discuss);
	
	public void removeDiscuss(Long discussId);
}
