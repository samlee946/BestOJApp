package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import com.example.administrator.bestojapp.ui.UserActivity_;

@EActivity(R.layout.activity_user)
public class UserActivity extends AppCompatActivity {

    private ResideMenu resideMenu;

    @Click({R.id.button_userpage_login})
    public void buttonOnClick(View view) {
        switch (view.getId()) {
            case R.id.button_userpage_login: {
                LoginActivity.actionStart(UserActivity.this);
                break;
            }
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UserActivity_.class);
        context.startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resideMenu = new ResideMenuGeneral(UserActivity.this, UserActivity.this).getResideMenu();
    }
}
