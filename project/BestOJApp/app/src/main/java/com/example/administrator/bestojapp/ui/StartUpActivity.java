package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.manager.UserManager;
import com.example.administrator.bestojapp.web.WebService;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.*;

import java.util.ArrayList;
import java.util.List;

import database.message.Message;

@EActivity(R.layout.activity_start_up)
public class StartUpActivity extends AppCompatActivity {

    private ResideMenu resideMenu;

    @RestService
    WebService webService;

    AccessManager accessManager;
    UserManager userManager;

    private List<Message> messageList = new ArrayList<Message>();

    ProgressDialog progressDialog;

    @ViewById(R.id.listView_messages)
    ListView listView_messages;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @UiThread
    public void showMessage() {
        ArrayAdapter adapter = new ArrayAdapter(StartUpActivity.this, android.R.layout.simple_list_item_1, messageList);
        listView_messages.setAdapter(adapter);
        listView_messages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = (Message) listView_messages.getItemAtPosition(position);
                if (message.getType() == 1) { //普通题目下的讨论
                    DiscussActivity.actionStart(StartUpActivity.this, message.getLinkedID());
                }
            }
        });
    }

    @Background
    public void loadMessages() {
        showProgressDialog(true);
        if(userManager.getIsLogin() == 1) {
            messageList = accessManager.getMessage(userManager.getUserName(), userManager.getPasswd());
            Log.d("loadMessages", messageList.toString());
            showMessage();
        }
        else {
            toastShort("读取消息失败，请先登陆。");
        }
        showProgressDialog(false);
    }

    @UiThread
    void toastShort(String msg) {
        Toast.makeText(StartUpActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, StartUpActivity_.class);
        context.startActivity(intent);
    }

    private void init() {
        accessManager = new AccessManager(StartUpActivity.this, webService);
        userManager = UserManager.getInstance(StartUpActivity.this);
        resideMenu = new ResideMenuGeneral(StartUpActivity.this, StartUpActivity.this).getResideMenu();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    }

    private void afterInit() {
        loadMessages();
    }

    /**
     * 用于显示正在加载的对话框
     * @param operation
     */
    @UiThread
    void showProgressDialog(boolean operation) {
        if(operation) progressDialog.show();
        else progressDialog.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        afterInit();
    }
}
