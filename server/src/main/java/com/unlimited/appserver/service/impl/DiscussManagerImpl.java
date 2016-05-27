package com.unlimited.appserver.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;

import com.unlimited.appserver.dao.IDiscussDAO;
import com.unlimited.appserver.dao.exception.DiscussNotFoundException;
import com.unlimited.appserver.model.Discuss;
import com.unlimited.appserver.service.DiscussManager;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.service.impl.GenericManagerImpl;

public class DiscussManagerImpl extends GenericManagerImpl<Discuss, Long> implements DiscussManager{

	private IDiscussDAO iDiscussDAO;
	
	public void saveDiscuss(Discuss discuss) throws DiscussNotFoundException {
		try {
			iDiscussDAO.save(discuss);
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new DiscussNotFoundException("No discuss found in this problem");
		}
	}
	
	public String getDiscussByProblemID(Long problemID) throws DiscussNotFoundException {
		String ret = "获取失败";
		try {
			ret = iDiscussDAO.getDiscussByProblemID(problemID);
			log.debug(ret);
			return ret;
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new DiscussNotFoundException("No discuss found in this problem");
		}
	}
	
	public Long getDiscussUserIDByDiscussID(Long discussId){
		Long ret = null;
		try {
			ret = iDiscussDAO.getDiscussUserIDByDiscussID(discussId);
			log.debug(ret);
			return ret;
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
		}
		return ret;
	}
	
	public boolean postDiscuss(Discuss discuss) {
		boolean ret = true;
		try {
			iDiscussDAO.save(discuss);
		} catch (Exception e) {
			// TODO: handle exception
			ret = false;
		}
		return true;
	}
	
	public void removeDiscuss(Long discussId) {
		iDiscussDAO.remove(iDiscussDAO.getDiscussByDiscussID(discussId));
	}

	/**
	 * @return the iDiscussDAO
	 */
	public IDiscussDAO getiDiscussDAO() {
		return iDiscussDAO;
	}

	/**
	 * @param iDiscussDAO the iDiscussDAO to set
	 */
	@Required
	public void setiDiscussDAO(IDiscussDAO iDiscussDAO) {
		this.iDiscussDAO = iDiscussDAO;
	}
}
