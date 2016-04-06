package com.example.administrator.bestojapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class StartUpActivity extends AppCompatActivity {

    private ResideMenu resideMenu;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_up);

        resideMenu = new ResideMenuGeneral(StartUpActivity.this, StartUpActivity.this).getResideMenu();

        /*
        Intent intent = new Intent();
        intent.setClass(StartUpActivity.this,MainActivity_.class);
        StartUpActivity.this.startActivity(intent);
        */

        /*
        Handler handler = new Handler();

        Runnable updateThread = new Runnable(){
            public void run(){
                Intent intent = new Intent();
                intent.setClass(StartUpActivity.this,MainActivity_.class);
                StartUpActivity.this.startActivity(intent);
                finish();
            }
        };
        //handler.postDelayed(updateThread, 2500);
        handler.postDelayed(updateThread, 100);

        */
    }
}
