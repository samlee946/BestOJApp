package com.unlimited.appserver.dao.hibernate;

import java.util.List;

import com.unlimited.appserver.dao.IAppUserDAO;
import com.unlimited.appserver.dao.exception.UserNotFoundException;
import com.unlimited.appserver.dao.exception.UsernameNotFoundException;
import com.unlimited.appserver.model.AppUser;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

public class AppUserDaoHibernate extends GenericDaoHibernate<AppUser, Long> implements IAppUserDAO {

	public AppUserDaoHibernate() {
		super(AppUser.class);
		// TODO Auto-generated constructor stub
	}

	public Long getUserIdByUsername(String username) throws UserNotFoundException {
		List users = getHibernateTemplate().find("from app_users where userName = " + username);
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException("Didn't find user whose username = " + username);
		}
		else {
			AppUser appUser = (AppUser) users.get(0);
			return appUser.getId();
		}
	}

	public boolean isUserExist(String userNameString, String passwordString) throws UserNotFoundException {
		// TODO Auto-generated method stub
		List users = getHibernateTemplate().find("from app_users where userName = " + userNameString + " and password = " + passwordString);
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException("Didn't find user whose username = " + userNameString + " and password = " + passwordString);
		}
		else {
			return true;
		}
	}

}
