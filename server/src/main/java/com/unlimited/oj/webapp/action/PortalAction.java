package com.unlimited.oj.webapp.action;

import com.unlimited.oj.dao.support.Page;
import com.unlimited.oj.model.Role;
import com.unlimited.oj.model.User;
import com.unlimited.oj.util.ApplicationConfig;
import com.unlimited.oj.util.Tool;
import java.io.File;

/**
 * Action for facilitating Problem Management feature.
 */
public class PortalAction extends BaseAction
{

    private Page newss;
    private String downloadFolder;
    private static final long serialVersionUID = 6776111111712115191L;

    /**
     * Grab the entity from the database before populating with request
     * parameters
     */
    public void prepare()
    {
        super.prepare();
    }

    /**
     * Default: just returns "success"
     *
     * @return "success"
     */
    public String execute()
    {
        return SUCCESS();
    }

    /**
     * Sends gameProblems to "mainMenu" when !from.equals("list"). Sends everyone
     * else to "cancel"
     *
     * @return "mainMenu" or "cancel"
     */
    public String cancel()
    {
        if (!"list".equals(from))
        {
            return "mainMenu";
        }
        return "cancel";
    }

    public String mainPage()
    {
        String sign = getRequest().getParameter("host");
        if (sign != null)
        {
            if (!"".equals(sign))
            {
                // 判断该SIGN是否有效，有效则可进入该人空间
                User user = null;
                try
                {
                    user = userManager.getUserByUsername(sign);
                } catch (Exception e)
                {
                }
                if (user != null)
                {
                    boolean flag = false;
                    for (Role role : user.getRoleList())
                    {
                        if (role.getName().equalsIgnoreCase("ROLE_CONTEST_HOLDER"))
                        {
                            flag = true;
                            break;
                        }
                    }
                    // 设置标志
                    if (flag)
                        getRequest().getSession().setAttribute("oj_sign", sign);
                }
            } else
                getRequest().getSession().removeAttribute("oj_sign");
        }

        return showBBS();
    }

    public String showBBS()
    {
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

        return SUCCESS();
    }

    public String delayShow()
    {
        String queryString = getRequest().getQueryString();
        if(queryString!=null)
            getRequest().getSession().setAttribute("_queryString", queryString);
        else
             getRequest().getSession().removeAttribute("_queryString");
        return "success";
    }

    public Page getNewss()
    {
        return newss;
    }

    public String deleteDownloadFiles()
    {
        File dir = new File(Tool.fixPath(ApplicationConfig.getApplicationRootPath() +
                    downloadFolder));
        if(!dir.exists())
            saveMessage(dir.getAbsolutePath() + " not exists.");
        else
        {
            Tool.delete(dir);
            saveMessage("delete done.");
        }
        return SUCCESS();
    }

    public String getDownloadFolder()
    {
        return downloadFolder;
    }

    public void setDownloadFolder(String downloadFolder)
    {
        this.downloadFolder = downloadFolder;
    }

}
