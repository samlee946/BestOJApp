package com.example.administrator.bestojapp.ui;

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

import com.example.administrator.bestojapp.Bean.DiscussJavaBean;
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
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;
import android.view.ContextMenu;
import android.widget.Toast;

@EActivity(R.layout.activity_discuss)
public class DiscussActivity extends AppCompatActivity {

    @ViewById(com.example.administrator.bestojapp.R.id.listView_discuss)
    ListView listView;

    @ViewById(com.example.administrator.bestojapp.R.id.button_post)
    Button button_post;

    @RestService
    WebService webService;

    AccessManager accessManager;

    private Long problemID;

    private Discusses discusses;

    private ResideMenu resideMenu;

    private List<DiscussJavaBean> discussJavaBeanList = new ArrayList<DiscussJavaBean>();

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
                PostActivity.actionStart(DiscussActivity.this, problemID);
                break;
            }
        }
    }

    @AfterViews
    void init() {
        problemID = getIntent().getLongExtra("problemID", 0L);
        getDiscussByProblemID(problemID);
        while(accessManager.getEcho() == -1) ;
        discusses = accessManager.getDiscusses();
        if(accessManager.getEcho() == 0) {
            for(DiscussJavaBean discussJavaBean : discusses.getDiscussJavaBeans()) {
                discussJavaBeanList.add(discussJavaBean);
            }
            DiscussAdapter adapter = new DiscussAdapter(DiscussActivity.this, R.layout.discuss_item, discussJavaBeanList);
            listView.setAdapter(adapter);
        }
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
        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                AdapterView.AdapterContextMenuInfo menuInfo;
                menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                int index = ((DiscussJavaBean)listView.getItemAtPosition((int)menuInfo.id)).getId();
                removeDiscuss(new Long((long)index));
                //toast("删除" + ((DiscussJavaBean)listView.getItemAtPosition((int)menuInfo.id)).getId());
                break;
            case 2:
                break;
        }
        return false;
    }

    @Background
    void removeDiscuss(Long discussId) {
        webService.removeDiscuss(discussId);
    }

    @UiThread
    void toast(String str) {
        Toast.makeText(DiscussActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accessManager = new AccessManager(DiscussActivity.this, webService);
        resideMenu = new ResideMenuGeneral(DiscussActivity.this, DiscussActivity.this).getResideMenu();
    }
}
