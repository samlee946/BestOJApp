package com.unlimited.oj.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.net.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unlimited.appserver.model.User;
import com.unlimited.appserver.service.DiscussManager;
import com.unlimited.appserver.service.UserManager;
import com.unlimited.oj.Constants;
import com.unlimited.oj.service.LoginLogManager;
import com.unlimited.oj.service.MailEngine;
import com.unlimited.oj.service.RoleManager;

import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.providers.encoding.PasswordEncoder;

/**
 * Implementation of <strong>ActionSupport</strong> that contains convenience
 * methods for subclasses. For example, getting the current user and saving
 * messages/errors. This class is intended to be a base class for all Action
 * classes.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseAction extends ActionSupport implements Preparable
{

    private static final long serialVersionUID = 3525445612504421307L;
    /**
     * Constant for cancel result String
     */
    public static final String CANCEL = "cancel";
    protected User currentUser;
    protected String oj_sign;
    
    protected String downloadPath;
    protected String downloadFolder;
    
    /**
     * Transient log to prevent session synchronization issues - children can
     * use instance for logging.
     */
    protected transient final Log log = LogFactory.getLog(getClass());
    protected LoginLogManager loginLogManager;
    protected UserManager userManager;
    protected RoleManager roleManager;
    protected DiscussManager discussManager;
	/**
     * Indicator if the user clicked cancel
     */
    protected String cancel;
    

	/**
     * Indicator for the page the user came from.
     */
    protected String from;
    /**
     * Set to "delete" when a "delete" request parameter is passed in
     */
    protected String delete;
    /**
     * Set to "save" when a "save" request parameter is passed in
     */
    protected String save;
    /**
     * MailEngine for sending e-mail
     */
    protected MailEngine mailEngine;
    /**
     * A message pre-populated with default data
     */
    protected SimpleMailMessage mailMessage;
    /**
     * Velocity template to use for e-mailing
     */
    protected String templateName;

    protected PasswordEncoder passwordEncoder;


    /**
     * Simple method that returns "cancel" result
     *
     * @return "cancel"
     */
    public String cancel()
    {
        return CANCEL;
    }


    protected void setNoCache()
    {
        getResponse().setHeader("_NoCache", "1");
    }
    /**
     * Save the message in the session, appending if messages already exist
     *
     * @param msg
     *            the message to put in the session
     */
    @SuppressWarnings("unchecked")
    protected void saveMessage(String msg)
    {
        List messages = (List) getRequest().getSession().getAttribute("messages");
        if (messages == null)
        {
            messages = new ArrayList();
            setNoCache();
        }
        messages.add(msg);
        getRequest().getSession().setAttribute("messages", messages);
    }

    /**
     * Convenience method to get the Configuration HashMap from the servlet
     * context.
     *
     * @return the user's populated form from the session
     */
    protected Map getConfiguration()
    {
        Map config = (HashMap) getSession().getServletContext().getAttribute(Constants.CONFIG);
        // so unit tests don't puke when nothing's been set
        if (config == null)
        {
            return new HashMap();
        }
        return config;
    }

    /**
     * Convenience method to get the request
     *
     * @return current request
     */
    protected HttpServletRequest getRequest()
    {
        return ServletActionContext.getRequest();
    }

    /**
     * Convenience method to get the response
     *
     * @return current response
     */
    protected HttpServletResponse getResponse()
    {
        return ServletActionContext.getResponse();
    }

    /**
     * Convenience method to get the session. This will create a session if one
     * doesn't exist.
     *
     * @return the session from the request (request.getSession()).
     */
    protected HttpSession getSession()
    {
        return getRequest().getSession();
    }

    protected ServletContext getServletContext()
    {
        return ServletActionContext.getServletContext();
    }

    /**
     * Convenience method to send e-mail to users
     *
     * @param user
     *            the user to send to
     * @param msg
     *            the message to send
     * @param url
     *            the URL to the application (or where ever you'd like to send
     *            them)
     */
    protected void sendUserMessage(User user, String msg, String url)
    {
        if (log.isDebugEnabled())
        {
            log.debug("sending e-mail to user [samlee946@gmail.com]...");
        }

        mailMessage.setTo(user.getUsername() + "<samlee946@gmail.com>");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        // TODO: figure out how to get bundle specified in struts.xml
        // model.put("bundle", getTexts());
        model.put("message", msg);
        model.put("applicationURL", url);
        mailEngine.sendMessage(mailMessage, templateName, model);
    }

    public void setRoleManager(RoleManager roleManager)
    {
        this.roleManager = roleManager;
    }

    public void setMailEngine(MailEngine mailEngine)
    {
        this.mailEngine = mailEngine;
    }

    public void setMailMessage(SimpleMailMessage mailMessage)
    {
        this.mailMessage = mailMessage;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    /**
     * Convenience method for setting a "from" parameter to indicate the
     * previous page.
     *
     * @param from
     *            indicator for the originating page
     */
    public void setFrom(String from)
    {
        this.from = from;
    }

    public void setDelete(String delete)
    {
        this.delete = delete;
    }

    public void setSave(String save)
    {
        this.save = save;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }


	/**
     *
     * @throws Exception
     */
    public void prepare()
    {
        // Set userManager
        ServletContext context = getRequest().getSession().getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        userManager = (UserManager) ctx.getBean("userManager");

        // Set currentUser
        String username = getRequest().getRemoteUser();
        if (username != null)
            currentUser = userManager.getUserByUsername(username);
        else
            currentUser = null;

        // set oj_sign
        oj_sign = (String) getRequest().getSession().getAttribute("oj_sign");

        // set the menu and submenu
        String menu = getRequest().getParameter("menu");
        if (menu != null && !"".equals(menu))
            getRequest().getSession().setAttribute("oj_menu", menu);
        String submenu = getRequest().getParameter("submenu");
/*
        if((submenu==null || "".equals(submenu)) && (menu == null || "".equals(menu) || "ExperimentMenu".equalsIgnoreCase(menu)))
        	submenu="ExperimentAdvanceLanguage";
        if((submenu==null || "".equals(submenu)) && "ExamMenu".equalsIgnoreCase(menu))
        	submenu="ExamList";
*/
        if (submenu != null && !"".equals(submenu))
            getRequest().getSession().setAttribute("oj_submenu", submenu);
    }

    public void setUserManager(UserManager userManager)
    {
        this.userManager = userManager;
    }

    public void setAuth_administrators(String auth_administrators)
    {
        getRequest().setAttribute("auth_administrators", auth_administrators);
    }

    public void setAuth_objectIdKeyName(String auth_objectIdKeyName)
    {
        getRequest().setAttribute("auth_objectIdKeyName", auth_objectIdKeyName);
    }

    public void setAuth_objectType(String auth_objectType)
    {
        getRequest().setAttribute("auth_objectType", auth_objectType);
    }

    public void setAuth_owners(String auth_owners)
    {
        getRequest().setAttribute("auth_owners", auth_owners);
    }

    public void setLastSubmitProblemId(Long id)
    {
        if (id != null)
            getRequest().getSession().setAttribute("_lastSubmitProblemId", id);
    }

    public Long getLastSubmitProblemId()
    {
        if (getRequest().getSession().getAttribute("_lastSubmitProblemId") != null)
            return (Long) getRequest().getSession().getAttribute("_lastSubmitProblemId");
        else
            return new Long(0);
    }

    public void setLastSubmitTime(Date date)
    {
        if (date != null)
            getRequest().getSession().setAttribute("_lastSubmitTime", date);
    }

    public Date getLastSubmitTime()
    {
        if (getRequest().getSession().getAttribute("_lastSubmitTime") != null)
            return (Date) getRequest().getSession().getAttribute("_lastSubmitTime");
        else
        {
            Calendar cal = Calendar.getInstance();
            cal.set(1900, 1, 1);
            return cal.getTime();
        }
    }

     public boolean isOJMonitorRun() throws IOException
    {
        if(getCurrentUser().isAdministrator())
            return true;
        
        Socket socket = null;
        BufferedReader in = null;
        try
        {
            String host = getRequest().getRemoteHost();
            if (host.equals("0:0:0:0:0:0:0:1"))
                host = "192.168.1.2";
            socket = new Socket();
            InetAddress ip = InetAddress.getByName(host);
            SocketAddress socketAddress = new InetSocketAddress(ip, 1909);
            socket.connect(socketAddress, 100);
            if (socket.isConnected())
            {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //            out.println("Hello");
                if (in.read() == 48)
                {
                    return true;
                }
            }
        } catch (IOException e)
        {
        } finally
        {
            if (in != null)
                in.close();
            if (socket != null && !socket.isClosed())
                socket.close();
        }
        return false;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    protected String SUCCESS()
    {
        if(hasActionErrors() || hasFieldErrors() ||
                getRequest().getSession().getAttribute("messages")!=null ||
                    getRequest().getSession().getAttribute("errors")!=null ||
                    getCurrentUser()!=null &&
                            (getCurrentUser().isAdministrator() ||
                             getCurrentUser().isContestAdministrator() ||
                             getCurrentUser().isExamAdministrator() ||
                             getCurrentUser().isExerciseAdministrator()))
            setNoCache();
        return SUCCESS;
    }

    protected String ERROR()
    {
        setNoCache();
        return ERROR;
    }

    protected String INPUT()
    {
        setNoCache();
        return INPUT;
    }

    public void setLoginLogManager(LoginLogManager loginLogManager)
    {
        this.loginLogManager = loginLogManager;
    }
    

	public LoginLogManager getLoginLogManager() {
		return loginLogManager;
	}


	public UserManager getUserManager() {
		return userManager;
	}


	public RoleManager getRoleManager() {
		return roleManager;
	}

    public String getDownloadPath()
    {
        return downloadPath;
    }

    public String getDownloadFolder()
    {
        return downloadFolder;
    }


	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}


	public void setDownloadFolder(String downloadFolder) {
		this.downloadFolder = downloadFolder;
	}


	/**
	 * @return the discussManager
	 */
	public DiscussManager getDiscussManager() {
		return discussManager;
	}


	/**
	 * @param discussManager the discussManager to set
	 */
	public void setDiscussManager(DiscussManager discussManager) {
		this.discussManager = discussManager;
	}
}
