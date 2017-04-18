package com.unlimited.appserver.service.impl;

import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.unlimited.appserver.dao.UserDao;
import com.unlimited.appserver.dao.exception.UserNotFoundException;
import com.unlimited.appserver.model.User;
import com.unlimited.appserver.service.UserManager;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.service.UserService;
import com.unlimited.oj.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import java.util.List;

/**
 * Implementation of UserManager interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@WebService(serviceName = "UserService", endpointInterface = "com.unlimited.oj.service.UserService")
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements
		UserManager, UserService {
	private UserDao dao;
	private PasswordEncoder passwordEncoder;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 *            the UserDao that communicates with the database
	 */
	@Required
	public void setUserDao(UserDao dao) {
		super.dao = dao;
		this.dao = dao;
	}

	/**
	 * Set the PasswordEncoder used to encrypt passwords.
	 * 
	 * @param passwordEncoder
	 *            the PasswordEncoder implementation
	 */
	@Required
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * {@inheritDoc}
	 */
	public User getUser(String userId) {
		return dao.get(new Long(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getUsers() {
		return dao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) throws UserExistsException {
		try {
			dao.save(user);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			log.warn(user.getUsername() + " " + user.getToken() + "User registering failed");
			throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeUser(String userId) {
		log.debug("removing user: " + userId);
		dao.remove(dao.get(new Long(userId)));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param username
	 *            the login name of the human
	 * @return User the populated user object
	 * @throws UsernameNotFoundException
	 *             thrown when username not found
	 */
	public User getUserByUsername(String username)
			throws UsernameNotFoundException {
		return (User) dao.getUserByUsername(username);
	}

	public User getUserByPKUAccount(String account) {
		try {
			return dao.getUserByPKUAccount(account);
		} catch (Exception e) {
			return null;
		}
	}

	public User getUserByEmail(String email) throws UsernameNotFoundException {
		try {
			return dao.getUserByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Long getUserIdByUsername(String username) {
		Long ret = 0L;
		try {
			ret = dao.getUserIdByUsername(username);
		} catch (UserNotFoundException e) {
			// TODO: handle exception
		}
		return ret;
	}
	
	public boolean isUserExist(String username, String password) {
			try {
				dao.isUserExist(username, password);
				return true;
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block	
				return false;
			}
	}
}
