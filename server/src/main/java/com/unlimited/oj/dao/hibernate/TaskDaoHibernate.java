/***********************************************************************
 * Module:  TaskDaoHibernate.java
 * Author:  develop
 * Purpose: Defines data access implementation class for class Task
 ***********************************************************************/
 
package com.unlimited.oj.dao.hibernate;

import java.util.*;
import java.io.Serializable;
import com.unlimited.oj.model.*;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.DaoException;

import com.unlimited.oj.dao.TaskDao;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;

/**
 * Class that implements CustomerDao interface
 * 
 */
public class TaskDaoHibernate extends GenericDaoHibernate<Task,Long> implements TaskDao 
{
	public TaskDaoHibernate()
	{
		super(Task.class);
		// TODO Auto-generated constructor stub
	}
}