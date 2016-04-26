/***********************************************************************
 * Module:  LoginLogDaoHibernate.java
 * Author:  benQ
 * Purpose: Defines data access implementation class for class LoginLog
 ***********************************************************************/
package com.unlimited.oj.dao.hibernate;

import java.util.*;
import java.io.Serializable;
import com.unlimited.oj.model.*;
import com.unlimited.oj.dao.GenericDao;
import com.unlimited.oj.dao.DaoException;

import com.unlimited.oj.dao.LoginLogDao;
import com.unlimited.oj.dao.hibernate.GenericDaoHibernate;
import com.unlimited.oj.dao.support.Page;
import org.hibernate.Criteria;

/**
 * Class that implements CustomerDao interface
 * 
 */
public class LoginLogDaoHibernate extends GenericDaoHibernate<LoginLog, Long> implements LoginLogDao
{

    public LoginLogDaoHibernate()
    {
        super(LoginLog.class);
        // TODO Auto-generated constructor stub
    }

    public void deleteAll()
    {
        Page page = pagedQuery("from LoginLog", 1, 10000);
        List temp = null;
        while((temp=page.getList())!=null && temp.size()>0)
        {
            getHibernateTemplate().deleteAll(temp);
            page = pagedQuery("from LoginLog", 1, 10000);
            getHibernateTemplate().clear();
        }
    }
}
