/**  */
package com.unlimited.appserver.service;

import com.unlimited.appserver.model.AppUser;
import com.unlimited.oj.service.UserExistsException;

/** 
 * @ClassName: AppUserManager 
 * @Description: TODO
 * @author Sam Lee
 * @date 2016-4-29 上午06:11:46 
 * @email samlee946@gmail.com
 *  
 */
public interface AppUserManager {
	
	public void saveAppUser(AppUser user) throws UserExistsException;

	public Long getUserIdByUsername(String username);
	
	public boolean isUserExist(String username, String password);
}
