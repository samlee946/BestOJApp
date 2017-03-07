package com.example.administrator.bestojapp.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

/**
 * Created by Administrator on 2016/4/7 0007.
 */
public class ResideMenuGeneral implements View.OnClickListener{

    private Context context;

    private Activity activity;

    private ResideMenu resideMenu;

    private ResideMenuItem itemUser;
    private ResideMenuItem itemProblem;
    private ResideMenuItem itemExam;
    private ResideMenuItem itemWiki;
    private ResideMenuItem itemBook;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemSetting;

    ResideMenuGeneral(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        setupMenu();
    }

    public void onClick(View view) {
        if(view == itemProblem) {
            MainActivity.actionStart(context);
        } else if(view == itemUser) {
            UserActivity.actionStart(context);
        } else if(view == itemSetting) {
            SettingsActivity.actionStart(context);
        } else if(view == itemExam) {
            ExamListActivity.actionStart(context);
        } else if(view == itemBook) {
            TestActivity.actionStart(context, 1L);
        }
        resideMenu.closeMenu();
    }

    private void setupMenu() {
        resideMenu = new ResideMenu(context);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(activity);
        resideMenu.setScaleValue(0.6f);
        //resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        itemUser = new ResideMenuItem(context, R.drawable.icon_profile, "个人中心");
        itemNews = new ResideMenuItem(context, R.drawable.icon_calendar, "用户资讯");
        itemProblem = new ResideMenuItem(context, R.drawable.icon_home, "刷题助手");
        itemExam = new ResideMenuItem(context, R.drawable.icon_exam, "考试管家");
        itemBook = new ResideMenuItem(context, R.drawable.icon_book, "好书推荐");
        itemSetting = new ResideMenuItem(context, R.drawable.icon_settings, "系统设置");

        itemUser.setOnClickListener(this);
        itemNews.setOnClickListener(this);
        itemProblem.setOnClickListener(this);
        itemExam.setOnClickListener(this);
        itemBook.setOnClickListener(this);
        itemSetting.setOnClickListener(this);

        resideMenu.addMenuItem(itemUser, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNews, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProblem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemExam, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemBook, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSetting, ResideMenu.DIRECTION_LEFT);
    }

    public ResideMenu getResideMenu() {
        return resideMenu;
    }
}
