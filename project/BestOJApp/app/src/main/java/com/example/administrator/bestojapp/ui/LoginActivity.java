package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestojapp.web.WebService;
import com.example.administrator.bestojapp.manager.UserManager;

import com.example.administrator.bestojapp.R;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;


@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private String usernameString;
    private String passwordString;
    private String responseString;

    private ResideMenu resideMenu;

    @ViewById(R.id.button_login)
    Button buttonLogin;

    @ViewById(R.id.button_register)
    Button buttonRegister;

    @ViewById(R.id.button_testLogin)
    Button buttonTestLogin;

    @ViewById(R.id.editText_username)
    EditText editTextUsername;

    @ViewById(R.id.editText_password)
    EditText editTextPassword;

    @ViewById
    TextView textViewDebug;

    @RestService
    WebService webService;

    @Click({R.id.button_login, R.id.button_register, R.id.button_testLogin, R.id.button_logout})
    public void buttonOnClicked(View view) {
        switch (view.getId()) {
            case R.id.button_login: {
                login();
                break;
            }
            case R.id.button_register: {
                register();
                break;
            }
            case R.id.button_testLogin: {
                testLogin();
                break;
            }
            case R.id.button_logout: {
                logout();
                break;
            }
        }
    }

    @Background
    void logout() {
        responseString = webService.logout();
        toastShort(responseString);
    }

    @Background
    void testLogin() {
        webService.testLogin();
    }

    @Background
    void login() {
        usernameString  = editTextUsername.getText().toString();
        passwordString = editTextPassword.getText().toString();
        responseString  = webService.login(usernameString, passwordString);
        UserManager.getInstance().setUserName(usernameString);
        UserManager.getInstance().setPasswd(passwordString);
        if(responseString.length() > 2) toastShort(responseString);
    }

    @Background
    void register() {
        usernameString  = editTextUsername.getText().toString();
        passwordString = editTextPassword.getText().toString();
        responseString  = webService.register(usernameString, passwordString);
        if(responseString.length() > 2) toastShort(responseString);
    }

    @UiThread
    void setAnimation(Button button) {
        FadingCircle fadingCircle = new FadingCircle();
    }

    @UiThread
    public void toastShort(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void toastLong(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_LONG).show();
    }

    @UiThread
    void setTextViewText(String textString) {
        textViewDebug.setText(textString);
    }

    @Background
    void checkNetwork() {
        try {
            toastShort(webService.checkNetwork());
        } catch (HttpClientErrorException e) {
            toastShort("网络连接错误!原因可能是:" + e.getMessage());
        } catch (ResourceAccessException e) {
            toastShort("网络连接错误!原因可能是:" + e.getMessage());
        }
    }

    @AfterViews
    void init() {
        /*
        toastLong("正在检查网络");
        checkNetwork();
        */
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity_.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resideMenu = new ResideMenuGeneral(LoginActivity.this, LoginActivity.this).getResideMenu();
    }
}
