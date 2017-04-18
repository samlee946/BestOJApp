package com.example.administrator.bestojapp.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class UserManager {
    private String userName;
    private String passwd;
    private String token;

    private Integer isLogin;

    private static UserManager userManager = null;

    SharedPreferences sharedPreferences;

    public UserManager() {}

    public UserManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        isLogin = sharedPreferences.getInt("auto_login", 0);
        if(isLogin == 1) {
            setUserName(sharedPreferences.getString("username", null));
            setPasswd(sharedPreferences.getString("password", null));
            setToken(sharedPreferences.getString("token", null));
        }
    }

    public static UserManager getInstance(Context context) {
        if(userManager == null) userManager = new UserManager(context);
        return userManager;
    }

    public void autoLogin(int flag) {
        if(flag == 1) {
            sharedPreferences.edit().putInt("auto_login", 1).apply();
            sharedPreferences.edit().putString("username", userName).apply();
            sharedPreferences.edit().putString("password", passwd).apply();
            sharedPreferences.edit().putString("token", token).apply();
        }
        else {
            sharedPreferences.edit().putInt("auto_login", 0).apply();
        }
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

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }


}
