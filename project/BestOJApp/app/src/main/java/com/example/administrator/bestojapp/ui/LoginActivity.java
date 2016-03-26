package com.example.administrator.bestojapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.WebService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private String usernameString;
    private String passwdString;
    private String responseString;

    @ViewById(R.id.button_login)
    Button buttonLogin;

    @ViewById(R.id.button_register)
    Button buttonRegister;

    @ViewById(R.id.editText_username)
    EditText editTextUsername;

    @ViewById(R.id.editText_passwd)
    EditText editTextPasswd;

    @ViewById
    TextView textViewDebug;

    @RestService
    WebService webService;

    @Click({R.id.button_login, R.id.button_register})
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
        }
    }

    @Background
    void login() {
        usernameString  = editTextUsername.getText().toString();
        passwdString    = editTextPasswd.getText().toString();
        responseString  = webService.register(usernameString, passwdString);
        setTextViewText(responseString);
    }

    @Background
    void register() {
        usernameString  = editTextUsername.getText().toString();
        passwdString    = editTextPasswd.getText().toString();
        responseString  = webService.register(usernameString, passwdString);
        setTextViewText(responseString);
    }

    @UiThread
    void setTextViewText(String textString) {
        textViewDebug.setText(textString);
    }

    @Background
    void checkNetwork() {
        try {
            //setTextViewText(webService.checkNetwork());
        } catch (HttpClientErrorException e) {
            setTextViewText("网络连接错误!原因可能是:" + e.getMessage());
        }
    }

    @AfterViews
    void init() {
        Toast.makeText(LoginActivity.this, "正在检查网络", Toast.LENGTH_SHORT).show();
        checkNetwork();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
