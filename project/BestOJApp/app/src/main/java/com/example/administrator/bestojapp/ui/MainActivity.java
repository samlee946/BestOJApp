package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity{

    private Integer echo;

    private Long parentIdExperiment = 1099509530623L;
    private Long parentIdDataStructure = 1099385798655L;
    private Long parentIdAdvancedProgram = 1099347001343L;

    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";
    private String jsonString = null;

    private AccessManager accessManager;

    private Intent intent = null;

    private ResideMenu resideMenu;

    @ViewById(R.id.button_homepage_login)
    Button button_login;

    @ViewById(R.id.button_homepage_data_structure)
    Button button_data_structure;

    @RestService
    WebService webService;

    @Click({R.id.button_homepage_login,
            R.id.button_homepage_experiment,
            R.id.button_homepage_data_structure,
            R.id.button_homepage_download_data,
            R.id.button_homepage_advanced_program,
            })
    void buttonOnClicked(View view) {
        switch (view.getId()) {
            case R.id.button_homepage_login: {
                intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity_.class);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_experiment: {
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentIdExperiment);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_data_structure: {
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentIdDataStructure);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_advanced_program: {
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentIdAdvancedProgram);
                startActivity(intent);
                break;
            }
        }
    }

    @UiThread
    void setButtonDataStructureEnable(boolean flag) {
        button_data_structure.setEnabled(flag);
    }

    @UiThread
    public void toastShort(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void toastLong(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity_.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accessManager = new AccessManager(MainActivity.this, webService);

        resideMenu = new ResideMenuGeneral(MainActivity.this, MainActivity.this).getResideMenu();
    }
}
