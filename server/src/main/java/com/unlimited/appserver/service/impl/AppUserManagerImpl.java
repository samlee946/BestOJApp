package com.unlimited.appserver.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;

import com.unlimited.appserver.dao.IAppUserDAO;
import com.unlimited.appserver.dao.exception.UserNotFoundException;
import com.unlimited.appserver.model.AppUser;
import com.unlimited.appserver.service.AppUserManager;
import com.unlimited.oj.model.User;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.service.impl.GenericManagerImpl;

public class AppUserManagerImpl extends GenericManagerImpl<AppUser, Long> implements AppUserManager{

	private IAppUserDAO iAppUserDAO;
	
	public void saveAppUser(AppUser user) throws UserExistsException {
		try
		{
			iAppUserDAO.save(user);
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new UserExistsException("User '" + user.getUserName() + "' already exists!");
		}
	}
	
	public Long getUserIdByUsername(String username) {
		Long ret = 0L;
		try {
			ret = iAppUserDAO.getUserIdByUsername(username);
		} catch (UserNotFoundException e) {
			// TODO: handle exception
		}
		return ret;
	}
	
	public boolean isUserExist(String username, String password) {
			try {
				iAppUserDAO.isUserExist(username, password);
				return true;
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block	
				return false;
			}
	}


	/**
	 * @return the iAppUserDAO
	 */
	public IAppUserDAO getiAppUserDAO() {
		return iAppUserDAO;
	}

	/**
	 * @param iAppUserDAO the iAppUserDAO to set
	 */
	@Required
	public void setiAppUserDAO(IAppUserDAO iAppUserDAO) {
		this.iAppUserDAO = iAppUserDAO;
	}
}
