package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.manager.UserManager;
import com.example.administrator.bestojapp.web.WebService;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Administrator on 2017/4/19.
 */
@EActivity(R.layout.activity_recommendation)
public class RecommendationActivity extends AppCompatActivity {

    private String problemID;

    private ResideMenu resideMenu;

    private AccessManager accessManager;

    ProgressDialog progressDialog;

    @ViewById(R.id.textView_problem_recommendation)
    TextView textView_problem_recommendation;

    @RestService
    WebService webService;

    UserManager userManager;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecommendationActivity_.class);
        context.startActivity(intent);
    }

    @Background
    void recommend() {
        if(userManager.getIsLogin() == 0) {
            toastShort("请先登陆！");
            return ;
        }
        problemID = webService.getProblemRecommendation(userManager.getToken());
        setText(problemID);
    }

    @Click(R.id.textView_problem_recommendation)
    void buttonOnClick(View view) {
        if(view.getId() == R.id.textView_problem_recommendation) ProblemActivity.actionStart(RecommendationActivity.this, Long.parseLong(problemID));
    }

    @UiThread
    public void toastShort(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void setText(String text) {
        textView_problem_recommendation.setText(text);
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

    @AfterViews
    void init() {
        resideMenu = new ResideMenuGeneral(RecommendationActivity.this, RecommendationActivity.this).getResideMenu();
        userManager = UserManager.getInstance(RecommendationActivity.this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        recommend();
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
