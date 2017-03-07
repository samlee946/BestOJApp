package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.bestojapp.Bean.Discusses;
import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;
import com.example.administrator.bestojapp.adapter.DiscussAdapter;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
//import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.rest.*;

import java.util.ArrayList;
import java.util.List;
import android.view.ContextMenu;
import android.widget.Toast;

import database.exam.discuss.Discuss;


@EActivity(R.layout.activity_discuss)
public class DiscussActivity extends AppCompatActivity {

    @ViewById(R.id.listView_discuss)
    ListView listView;

    @ViewById(R.id.button_post)
    Button button_post;

    @RestService
    WebService webService;

    AccessManager accessManager;

    private Long problemID;

    private Discusses discusses;

    private ResideMenu resideMenu;

    private List<Discuss> discussList = new ArrayList<Discuss>();

    ProgressDialog progressDialog;

    public static void actionStart(Context context, Long problemID) {
        Intent intent = new Intent(context, DiscussActivity_.class);
        intent.putExtra("problemID", problemID);
        context.startActivity(intent);
    }

    @Background
    public void getDiscussByProblemID(Long problemID) {
        accessManager.getDiscussByProblemId(problemID);
    }

    @Click(R.id.button_post)
    void buttonOnClick(View view) {
        switch (view.getId()) {
            case R.id.button_post: {
                PostActivity.actionStart(DiscussActivity.this, problemID, null);
                break;
            }
        }
    }

    @UiThread
    void showDiscuss() {
        DiscussAdapter adapter = new DiscussAdapter(DiscussActivity.this, R.layout.discuss_item, discussList);
        listView.setAdapter(adapter);
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            public void onCreateContextMenu(ContextMenu conMenu, View view, ContextMenu.ContextMenuInfo info) {
                conMenu.setHeaderTitle("选择操作");
                conMenu.add(0, 0, 0, "回复");
                conMenu.add(0, 1, 0, "删除该条讨论");
                conMenu.add(0, 2, 0, "封禁该名用户");

                   /* Add as many context-menu-options as you want to. */
            }
        });
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        Long index;
        switch (item.getItemId()) {
            case 0: //回复
                menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                index = ((Discuss)listView.getItemAtPosition((int)menuInfo.id)).getId();
                PostActivity.actionStart(DiscussActivity.this, problemID, index);
                break;
            case 1: //删除
                menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                index = ((Discuss)listView.getItemAtPosition((int)menuInfo.id)).getId();
                removeDiscuss(index);
                break;
            case 2: //封禁
                break;
        }
        return false;
    }

    @Background
    void removeDiscuss(Long discussId) {
        accessManager.removeDiscuss(discussId);
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

    @UiThread
    void toastShort(String str) {
        Toast.makeText(DiscussActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Background
    void loadDiscuss() {
        showProgressDialog(true);
        discussList = accessManager.getDiscussByProblemId(problemID);
        if(discussList == null || discussList.isEmpty()) {
            toastShort(getString(R.string.fail_loading_discuss));
        }
        else {
            showDiscuss();
        }
        showProgressDialog(false);
    }

    void init() {
        problemID = getIntent().getLongExtra("problemID", 0L);
        accessManager = new AccessManager(DiscussActivity.this, webService);
        resideMenu = new ResideMenuGeneral(DiscussActivity.this, DiscussActivity.this).getResideMenu();
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    }

    void afterInit() {
        loadDiscuss();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        afterInit();
    }
}
