package com.example.administrator.bestojapp.manager;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class UserManager {
    private String userName;
    private String passwd;
    private String token;

    private static UserManager userManager = null;

    public static UserManager getInstance() {
        if(userManager == null) userManager = new UserManager();
        return userManager;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
