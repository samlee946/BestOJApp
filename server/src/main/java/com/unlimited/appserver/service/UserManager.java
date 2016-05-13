package com.unlimited.appserver.service;

import java.util.List;

import org.springframework.security.userdetails.UsernameNotFoundException;

import com.unlimited.appserver.dao.UserDao;
import com.unlimited.appserver.dao.exception.UserNotFoundException;
import com.unlimited.appserver.model.User;
import com.unlimited.oj.service.GenericManager;
import com.unlimited.oj.service.UserExistsException;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a> 
 */
public interface UserManager extends GenericManager<User, Long> {

    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setUserDao(UserDao userDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    User getUser(String userId);

    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    User getUserByUsername(String username) throws UsernameNotFoundException;

    User getUserByPKUAccount(String account);

    User getUserByEmail(String email) throws UsernameNotFoundException;

	/**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List getUsers();

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    void saveUser(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);
    
    /**
     * 通过用户名获取用户id
     * @Title: getUserIdByUsername 
     * @Description: TODO 
     * @param @param username
     * @param @return
     * @return Long
     * @throws
     */
    public Long getUserIdByUsername(String username);
	
    /**
     * 通过用户名和密码验证用户是否存在
     * @Title: isUserExist 
     * @Description: TODO 
     * @param @param username
     * @param @param password
     * @param @return
     * @return boolean
     * @throws
     */
	public boolean isUserExist(String username, String password);

}