package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends AppCompatActivity {

    private OJService ojService;

    @RestService
    WebService webService;

    private ResideMenu resideMenu;

    @Click({
            R.id.button_settingspage_delete_treenode,
            })
    void buttonOnClick(View view) {
        switch (view.getId()) {
            case R.id.button_settingspage_delete_treenode: {
                    ojService.deleteTreeNode();
                    break;
            }
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingsActivity_.class);
        context.startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ojService = new OJService(SettingsActivity.this, webService);
        resideMenu = new ResideMenuGeneral(SettingsActivity.this, SettingsActivity.this).getResideMenu();
    }
}
