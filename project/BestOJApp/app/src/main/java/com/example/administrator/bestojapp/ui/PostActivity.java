package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;
import com.example.administrator.bestojapp.manager.UserManager;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.activity_post)
public class PostActivity extends AppCompatActivity {

    private Long problemID;

    private Long replyID;

    private ResideMenu resideMenu;

    private AccessManager accessManager;

    @ViewById(R.id.editText_discuss_title)
    EditText editText_title;

    @ViewById(R.id.editText_discuss_content)
    EditText editText_content;

    @RestService
    WebService webService;

    UserManager userManager = UserManager.getInstance();

    public static void actionStart(Context context, Long problemID, Long replyID) {
        Intent intent = new Intent(context, PostActivity_.class);
        intent.putExtra("problemId", problemID);
        if(replyID != null) intent.putExtra("replyID", replyID);
        context.startActivity(intent);
    }

    @Click(R.id.button_post_discuss)
    void buttonOnClick(View view) {
        switch (view.getId()) {
            case R.id.button_post_discuss: {
                post();
                break;
            }
        }
    }

    @Background
    void post() {
        String username = userManager.getUserName();
        String password = userManager.getPasswd();
        String title = editText_title.getText().toString();
        String content = editText_content.getText().toString();
        if(username != null && password != null) {
            String responseString = accessManager.postDiscuss(username, password, title, content, problemID, replyID);
            if(responseString != null) {
                toastShort(responseString);
            } else {
                toastShort(getString(R.string.fail_posting));
            }
        } else {
            toastShort(getString(R.string.input_username_and_password_first));
        }
    }

    @UiThread
    public void toastShort(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_SHORT).show();
    }

    @AfterViews
    void init() {
        resideMenu = new ResideMenuGeneral(PostActivity.this, PostActivity.this).getResideMenu();
        accessManager = new AccessManager(PostActivity.this, webService);
        problemID = getIntent().getLongExtra("problemId", 0L);
        replyID = getIntent().getLongExtra("replyID", -1L);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
