package com.unlimited.appserver.dao;

import org.springframework.transaction.annotation.Transactional;

import com.unlimited.appserver.dao.exception.UserNotFoundException;
import com.unlimited.appserver.dao.exception.UsernameNotFoundException;
import com.unlimited.appserver.model.AppUser;
import com.unlimited.oj.dao.GenericDao;

/**
 * 
 * @ClassName: IAppUserDAO 
 * @Description: AppUserDAO接口
 * @author Sam Lee
 * @date 2016-4-29 上午05:42:54 
 * @email samlee946@gmail.com
 *
 */
public interface IAppUserDAO extends GenericDao<AppUser, Long>{
	
    @Transactional
	public Long getUserIdByUsername(String username) throws UserNotFoundException;
    
    @Transactional
    public boolean isUserExist(String userNameString, String passwordString) throws UserNotFoundException;
}
