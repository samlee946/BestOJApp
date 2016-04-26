package com.unlimited.oj.webapp.action;


import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationTrustResolver;
import org.springframework.security.AuthenticationTrustResolverImpl;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.struts2.ServletActionContext;
import com.unlimited.oj.Constants;
import com.unlimited.oj.dao.support.Page;
import com.unlimited.oj.enums.*;
import com.unlimited.oj.model.Role;
import com.unlimited.oj.model.User;
import com.unlimited.oj.pojo.DataChart;
import com.unlimited.oj.service.UserExistsException;
import com.unlimited.oj.util.Tool;
import com.unlimited.oj.webapp.util.RequestUtil;
import org.springframework.mail.MailException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Action for facilitating User Management feature.
 */
public class UserAction extends BaseAction
{

    private static final long serialVersionUID = 6776558938712115191L;
    private Page users;
    private User user;
    private String id;
    private DataChart dataChartGlobal;
    private DataChart dataChartRecent;
    private String searchKey;
    private String searchValue;
    private String addBatchUserData; // 用于addBatchUsers方法

    /**
     * Grab the entity from the database before populating with request
     * parameters
     */
    @Override
    public void prepare()
    {
        super.prepare();

        if (getRequest().getMethod().equalsIgnoreCase("get"))
        {
            // if a user's id is passed in
            String userId = getRequest().getParameter("id");
            if (userId != null && !"".equals(userId))
            {
                user = userManager.getUser(getRequest().getParameter("id"));
            }
        } else if (getRequest().getMethod().equalsIgnoreCase("post"))
        {
            // prevent failures on new
            if (getRequest().getParameter("user.id") != null && !"".equals(getRequest().getParameter("user.id")))
            {
                user = userManager.getUser(getRequest().getParameter("user.id"));
            }
        }
    }

    /**
     * Holder for users to display on list screen
     *
     * @return list of users
     */
    public Page getUsers()
    {
        return users;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * Delete the user passed in.
     *
     * @return success
     * @throws IOException
     */
    public String user_delete_PUBLIC() throws IOException
    {
        if (!getRequest().isUserInRole(Constants.ADMIN_ROLE))
        {
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return "mainPage";
        }

        userManager.removeUser(user.getId().toString());

        List<String> args = new ArrayList<String>();
        args.add(user.getFullName());
        saveMessage(getText("user.deleted", args));

        user_list_PUBLIC();
        return SUCCESS();
    }

    public String user_editProfile_PUBLIC() throws IOException
    {
    	user = getCurrentUser();
    	
        user.setOldPassword(user.getPassword()); // set old password =
        // password

        if (user.getUsername() != null)
        {
            user.setConfirmPassword(user.getPassword());

            // if user logged in with remember me, display a warning that they
            // can't change passwords
            log.debug("checking for remember me login...");

            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
            SecurityContext ctx = SecurityContextHolder.getContext();

            if (ctx != null)
            {
                Authentication auth = ctx.getAuthentication();

                if (resolver.isRememberMe(auth))
                {
                    getSession().setAttribute("cookieLogin", "true");
                    saveMessage(getText("userProfile.cookieLogin"));
                }
            }
        }

        return SUCCESS();
    }
    
    /**
     * Grab the user from the database based on the "id" passed in.
     *
     * @return success if user found
     * @throws IOException
     *             can happen when sending a "forbidden" from
     *             response.sendError()
     */
    public String user_edit_ADMIN() throws IOException
    {
        if(user==null)
        {
        	saveMessage("user is null");
        	return SUCCESS();
        }
        if (user != null && !user.getUsername().equals(getCurrentUser().getUsername()) &&
                !getCurrentUser().isAdministrator() &&
                !((getCurrentUser().isContestAdministrator() || getCurrentUser().isExerciseAdministrator()) &&
                !user.isAdministrator() && !user.isContestAdministrator() && !user.isExerciseAdministrator() &&
                !user.isExamAdministrator() && user.getGrade() < 5000))
        {
            addActionError("You have no right to edit [" + user.getUsername() + "]");
            return ERROR();
        }
        user.setOldPassword(user.getPassword()); // set old password =
        // password

        if (user.getUsername() != null)
        {
            user.setConfirmPassword(user.getPassword());

            // if user logged in with remember me, display a warning that they
            // can't change passwords
            log.debug("checking for remember me login...");

            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
            SecurityContext ctx = SecurityContextHolder.getContext();

            if (ctx != null)
            {
                Authentication auth = ctx.getAuthentication();

                if (resolver.isRememberMe(auth))
                {
                    getSession().setAttribute("cookieLogin", "true");
                    saveMessage(getText("userProfile.cookieLogin"));
                }
            }
        }

        return SUCCESS();
    }

    /**
     * Default: just returns "success"
     *
     * @return "success"
     */
    @Override
    public String execute()
    {
        return SUCCESS();
    }

    /**
     * Sends users to "mainMenu" when !from.equals("list"). Sends everyone else
     * to "cancel"
     *
     * @return "mainMenu" or "cancel"
     */
    @Override
    public String cancel()
    {
        if (!"list".equals(from))
        {
            return "mainMenu";
        }
        return "cancel";
    }

    public String user_editProfile_Submit_PUBLIC() throws Exception
    {
    	if(user==null)
    	{
    		saveMessage("user is null");
    		return SUCCESS();
    	}
        try
        {
            userManager.saveUser(user);
        } catch (AccessDeniedException ade)
        {
            // thrown by UserSecurityAdvice configured in aop:advisor
            // userManagerSecurity
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return INPUT();
        } catch (UserExistsException e)
        {
            List<String> args = new ArrayList<String>();
            args.add(user.getUsername());
            args.add(user.getEmail());
            addActionError(getText("errors.existing.user", args));

            // redisplay the unencrypted passwords
            user.setPassword(user.getConfirmPassword());
            return INPUT();
        }
        addActionMessage(getText("user.updated"));
        return SUCCESS();
    }
    
    /**
     * Save user
     *
     * @return success if everything worked, otherwise input
     * @throws IOException
     *             when setting "access denied" fails on response
     */
    public String user_edit_Submit_PUBLIC() throws Exception
    {

        boolean isNew = (user.getId() == null);
        // only attempt to change roles if user is admin
        // for other users, prepa(re() method will handle populating
        if (isNew)
        {
            addActionError("Please register first");
            return ERROR();
        }

        if (getCurrentUser() == null)
        {
            addActionError("Current user is null");
            return ERROR();
        }
        if (!user.getUsername().equals(getCurrentUser().getUsername()) &&
                !getCurrentUser().isAdministrator() &&
                !((getCurrentUser().isContestAdministrator() || getCurrentUser().isExerciseAdministrator()) &&
                !user.isAdministrator() && !user.isContestAdministrator() && !user.isExerciseAdministrator() &&
                !user.isExamAdministrator() && user.getGrade() < 5000)) // Version>=6为校队队员
        {
            addActionError("You have no right to save [" + user.getUsername() + "]");
            return ERROR();
        }
        if (getCurrentUser().isAdministrator())
        {
            user.getRoleList().clear(); // APF-788: Removing roles from user
            // doesn't work
            String[] userRoles = getRequest().getParameterValues("userRoles");

            for (int i = 0; userRoles != null && i < userRoles.length; i++)
            {
                String roleName = userRoles[i];
                Role role = roleManager.getRole(roleName);
                if (role != null)
                {
                    try
                    {
                        user.addRoleList(role);
                    } catch (Exception e)
                    {
                    }
                } else
                {
                    log.info("ROLE[" + roleName + "] is null.");
                }
            }
        }
        try
        {
            if (getCurrentUser().isAdministrator())
            {
                if (getRequest().getParameter("user.enabled") != null)
                {
                    user.setEnabled(true);
                } else
                {
                    user.setEnabled(false);
                }
            }
            userManager.saveUser(user);
        } catch (AccessDeniedException ade)
        {
            // thrown by UserSecurityAdvice configured in aop:advisor
            // userManagerSecurity
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return INPUT();
        } catch (UserExistsException e)
        {
            List<String> args = new ArrayList<String>();
            args.add(user.getUsername());
            args.add(user.getEmail());
            addActionError(getText("errors.existing.user", args));

            // redisplay the unencrypted passwords
            user.setPassword(user.getConfirmPassword());
            return INPUT();
        }
        addActionMessage(getText("user.updated"));
        return SUCCESS();
    }

    /**
     * Fetch all users from database and put into local "users" variable for
     * retrieval in the UI.
     *
     * @return "success" if no exceptions thrown
     */
    public String user_list_PUBLIC()
    {
        //users = userManager.getUsers();
        int page = 1, sort = 1, order = 2;
        try
        {
            page = Integer.parseInt(getRequest().getParameter("page"));
        } catch (Exception e)
        {// not need handle
        }
        try
        {
            sort = Integer.parseInt(getRequest().getParameter("sort"));
        } catch (Exception e)
        {// not need handle
        }
        try
        {
            order = Integer.parseInt(getRequest().getParameter("order"));
        } catch (Exception e)
        {// not need handle
        }
        users = userManager.getPage(page, -1, null, false);
        List<User> list = users.getList();
        List newList = new ArrayList();
        User preUser = null;
        int n = 0;
        for (User u : list)
        {
            if (preUser != null && u.getId().equals(preUser.getId()))
            {
                n++;
            } else
            {
                newList.add(u);
            }
            ;
            preUser = u;
        }
        if (n > 0)
        {
            users.setList(newList);
        }

        return SUCCESS();
    }
    
    public String user_listBySearch_ADMIN()
    {
        //users = userManager.getUsers();
        int page = 1, sort = 1, order = 2;
        if (getRequest().getParameter("searchKey") != null && !getRequest().getParameter("searchKey").equals(""))
        {
            searchKey = getRequest().getParameter("searchKey");
        }
        if (getRequest().getParameter("searchValue") != null && !getRequest().getParameter("searchValue").equals(""))
        {
            searchValue = getRequest().getParameter("searchValue");
        }

        try
        {
            page = Integer.parseInt(getRequest().getParameter("page"));
        } catch (Exception e)
        {// not need handle
        }
        try
        {
            sort = Integer.parseInt(getRequest().getParameter("sort"));
        } catch (Exception e)
        {// not need handle
        }
        try
        {
            order = Integer.parseInt(getRequest().getParameter("order"));
        } catch (Exception e)
        {// not need handle
        }
        if (searchValue == null || searchValue.equals(""))
        {
            users = userManager.getPage(page, -1, "username", true);
        } else
        {
            users = userManager.getPageBySearch(page, -1, searchKey, searchValue, "username", true);
        }
        List<User> list = users.getList();
        List newList = new ArrayList();
        User preUser = null;
        int n = 0;
        for (User u : list)
        {
            if (preUser != null && u.getId().equals(preUser.getId()))
            {
                n++;
            } else
            {
                newList.add(u);
            }
            ;
            preUser = u;
        }
        if (n > 0)
        {
            users.setList(newList);
        }

        return SUCCESS();
    }

    public DataChart getDataChartGlobal()
    {
        return dataChartGlobal;
    }

    public DataChart getDataChartRecent()
    {
        return dataChartRecent;
    }

    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }

    public String getSearchKey()
    {
        return searchKey;
    }

    public String getSearchValue()
    {
        return searchValue;
    }
    
    public String user_addBatch_ADMIN()
    {
    	return SUCCESS();
    }
    
    public String user_addBatch_Submit_ADMIN()
    {
        String sUserName, sPass, nick, className, sLine;
        Long gameId = null;

        if(addBatchUserData==null)
        {
        	saveMessage("addBatchUserData is null");
        	return "mainPage";
        }
        Scanner cin = new Scanner(new StringReader(addBatchUserData));
        while (cin.hasNext())
        {
            sLine = cin.nextLine();
            sLine = Tool.StringsReplace(sLine, "，", ",");
//System.err.println(sLine);
            String[] info = sLine.split(",");
            if (info.length < 2)
                continue;
            sUserName = info[0];
            sPass = info[1];
            if(info.length>2) nick = info[2];
            else nick = "";
            if(info.length>3) className = info[3];
            else className = "";
//System.out.printf(sUserName + " ");
//System.out.println(sPass );

            User user = null;
            try
            {
                user = userManager.getUserByUsername(sUserName);
            } catch (Exception ex)
            {
            }
            if (user == null)
            {// 创建该帐号
                user = new User();
                user.setClassName(className);
                user.setNick(nick);
                user.setEmail("");
                user.setFirstName("");
                user.setLastName("");
                user.setPassword(sPass);
                user.setConfirmPassword(sPass);
                user.setPhoneNumber("");
                user.setSchool("");
                user.setStudentNumber("");
                user.setUsername(sUserName);
                user.addRoleList(roleManager.getRole(Constants.USER_ROLE));
                user.setRegTime(new java.util.Date(System.currentTimeMillis())); // 设置帐号注册时间
                user.setEnabled(true);
                try
                {
                    userManager.saveUser(user);
                } catch (UserExistsException ex)
                {
                    Logger.getLogger(AdminAction.class.getName()).log(Level.SEVERE, null, ex);
                }

                saveMessage("Account [" + sUserName + "] is added.");
            }
            else
                saveMessage("Account [" + sUserName + "] already exists.");
        }

        return SUCCESS();
    }

    
    public void setAddBatchUserData(String addBatchUserData)
    {
        this.addBatchUserData = addBatchUserData;
    }
    
    public String user_listActiveUser_ADMIN()
    {
    	return SUCCESS();
    }

}
