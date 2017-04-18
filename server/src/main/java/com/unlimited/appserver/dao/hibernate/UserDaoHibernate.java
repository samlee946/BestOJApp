package com.unlimited.appserver.dao.hibernate;

import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.unlimited.appserver.dao.UserDao;
import com.unlimited.appserver.dao.exception.UserNotFoundException;
import com.unlimited.appserver.model.User;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import javax.persistence.Table;
import java.util.List;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler</a> Extended to
 *         implement Acegi UserDetailsService interface by David Carter
 *         david@carter.net Modified by <a href="mailto:bwnoll@gmail.com">Bryan
 *         Noll</a> to work with the new BaseDaoHibernate implementation that
 *         uses generics.
 */
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService
{

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public UserDaoHibernate()
	{
		super(User.class);
	}

	/**
	 * {@inheritDoc}
	 */
	// @SuppressWarnings("unchecked")
	// public List<User> getUsers() {
	// return getHibernateTemplate().find("from User u order by
	// upper(u.user_name)");
	// }
	/**
	 * {@inheritDoc}
	 */
	// public User saveUser(User user) {
	// log.debug("user's id: " + user.getId());
	// getHibernateTemplate().saveOrUpdate(user);
	// necessary to throw a DataIntegrityViolation and catch it in UserManager
	// getHibernateTemplate().flush();
	// return user;
	// }
	/**
	 * Overridden simply to call the saveUser method. This is happenening
	 * because saveUser flushes the session and saveObject of BaseDaoHibernate
	 * does not.
	 * 
	 * @param user
	 *            the user to save
	 * @return the modified user (with a primary key set if they're new)
	 */
	// @Override
	// public void save(User user) {
	// this.saveUser(user);
	// }
	/**
	 * {@inheritDoc}
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return (UserDetails) getUserByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserPassword(String username)
	{
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
		Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
		return jdbcTemplate.queryForObject("select password from " + table.name() + " where user_name= '" + username + "'", String.class);

	}

	public User getUserByUsername(String username) throws UsernameNotFoundException
	{
		List users = getHibernateTemplate().find("from User where user_name='" + username + "'");
		if (users == null || users.isEmpty())
		{
			throw new UsernameNotFoundException("user '" + username + "' not found...");
		} else
		{
			return (User) users.get(0);
		}
	}

	public Long getUserIdByUsername(String username) throws UserNotFoundException {
		List users = getHibernateTemplate().find("from User where user_name = '" + username + "'");
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException("Didn't find user whose username = " + username);
		}
		else {
			User user = (User) users.get(0);
			return user.getId();
		}
	}
	
	public boolean isUserExist(String userNameString, String passwordString) throws UserNotFoundException {
		// TODO Auto-generated method stub
		List users = getHibernateTemplate().find("from User where user_name = '" + userNameString + "' and password = '" + passwordString + "'");
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException("Didn't find user whose user_name = " + userNameString + " and password = " + passwordString);
		}
		else {
			return true;
		}
	}

	public User getUserByPKUAccount(String account) throws UsernameNotFoundException
	{
        if(account == null)
            return null;
		List users = getHibernateTemplate().find("from User u where u.pkuAccountValid=1 and u.pkuAccount=?", account);
		if (users == null || users.isEmpty())
		{
			return null;
		} else
		{
			return (User) users.get(0);
		}
	}

    public User getUserByEmail(String email) throws UsernameNotFoundException
    {
        if (email==null)
            return null;
		List users = getHibernateTemplate().find("from User u where u.enabled=1 and lower(u.email)=?", email.toLowerCase());
		if (users == null || users.isEmpty())
		{
			return null;
		} else
		{
			return (User) users.get(0);
		}
    }

}
