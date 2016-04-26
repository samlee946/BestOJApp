/***********************************************************************
 * Module:  LoginLogDao.java
 * Author:  benQ
 * Purpose: Defines data access interface for class LoginLog
 ***********************************************************************/
 
package com.unlimited.oj.dao;

import java.util.*;
import java.io.Serializable;
import com.unlimited.oj.model.*;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.DaoException;

/**
 * DAO interface that defines data access methods for class LoginLog
 * 
 */
public interface LoginLogDao extends GenericDao<LoginLog,Long> 
{  
    public void deleteAll();
}