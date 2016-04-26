package com.unlimited.oj.service.impl;

import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UsernameNotFoundException;
import com.unlimited.oj.dao.UserDao;
import com.unlimited.oj.model.User;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.service.UserManager;
import com.unlimited.oj.service.UserService;
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
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements UserManager, UserService
{
	private UserDao dao;
	private PasswordEncoder passwordEncoder;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 *            the UserDao that communicates with the database
	 */
	@Required
	public void setUserDao(UserDao dao)
	{
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
	public void setPasswordEncoder(PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * {@inheritDoc}
	 */
	public User getUser(String userId)
	{
		return dao.get(new Long(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getUsers()
	{
		return dao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveUser(User user) throws UserExistsException
	{

		if (user.getId() == null)
		{
			// if new user, lowercase userId
			user.setUsername(user.getUsername().toLowerCase());
		}

		// Get and prepare password management-related artifacts
		boolean passwordChanged = false;
		if (passwordEncoder != null)
		{// dao.getUserPassword(user.getUsername()) vs user.getPassword() is
			// always same, need other method, Checkie 2008.08.22
			// Check whether we have to encrypt (or re-encrypt) the password
			if (user.getId() == null)
			{
				// New user, always encrypt
				passwordChanged = true;
			} else if (!user.getPassword().equals(user.getOldPassword()))
			{
				passwordChanged = true;
			}

			// If password was changed (or new user), encrypt it
			if (passwordChanged)
			{
				log.warn(user.getUsername() + "'s password is changed");
				user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
				user.setConfirmPassword(user.getPassword());
				user.setOldPassword(user.getPassword());
			}
		} else
		{
			log.warn("PasswordEncoder not set, skipping password encryption...");
		}

		try
		{
			dao.save(user);
		} catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
		} catch (EntityExistsException e)
		{ // needed for JPA
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeUser(String userId)
	{
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
	public User getUserByUsername(String username) throws UsernameNotFoundException
	{
		return (User) dao.getUserByUsername(username);
	}

	public User getUserByPKUAccount(String account)
	{
		try
		{
			return dao.getUserByPKUAccount(account);
		} catch (Exception e)
		{
			return null;
		}
	}
	
	public User getUserByEmail(String email) throws UsernameNotFoundException
	{
		try
		{
			return dao.getUserByEmail(email);
		} catch (Exception e)
		{
			return null;
		}
	}
}
