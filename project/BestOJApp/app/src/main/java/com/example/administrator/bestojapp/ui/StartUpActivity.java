package com.example.administrator.bestojapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.bestojapp.R;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
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
    }
}
