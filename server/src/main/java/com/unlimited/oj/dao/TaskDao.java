/***********************************************************************
 * Module:  TaskDao.java
 * Author:  develop
 * Purpose: Defines data access interface for class Task
 ***********************************************************************/
 
package com.unlimited.oj.dao;

import java.util.*;
import java.io.Serializable;
import com.unlimited.oj.model.*;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.DaoException;

/**
 * DAO interface that defines data access methods for class Task
 * 
 */
public interface TaskDao extends GenericDao<Task,Long> 
{  

}