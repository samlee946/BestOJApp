package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import database.exam.Exam;
import com.example.administrator.bestojapp.Bean.ExamList;
import com.example.administrator.bestojapp.R;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;
import com.example.administrator.bestojapp.adapter.ExamAdapter;

import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试列表的activity
 */
@EActivity(R.layout.activity_exam_list)
public class ExamListActivity extends AppCompatActivity {

    @RestService
    WebService webService;

    @ViewById(R.id.listView_exam_list)
    ListView listView;

    private ResideMenu resideMenu;

    private AccessManager accessManager;

    ProgressDialog progressDialog;

    /** 服务器返回的考试列表 */
    private ExamList examList;

    /** 另一种格式的列表... */
    List<Exam> exams = new ArrayList<Exam>();

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ExamListActivity_.class);
        context.startActivity(intent);
    }

    @UiThread
    void toastShort(String msg) {
        Toast.makeText(ExamListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Background
    void loadExamList() {
        showProgressDialog(true);
        accessManager.getExamListFromServer();
        examList = accessManager.getExamList();
        showExamList();
        showProgressDialog(false);
        Log.d("solution", "loadExamList: " + examList.toString());
    }

    @UiThread
    void showExamList() {
        if(examList == null || examList.getEcho() != 0) {
            toastShort("获取考试信息失败，请检查网络连接");
        }
        else {
            for(Exam exam : examList.getExams()) {
                exams.add(exam);
            }
            ExamAdapter adapter = new ExamAdapter(ExamListActivity.this, R.layout.exam_item, exams);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Exam exam = (Exam) listView.getItemAtPosition(position);
                    ExamNodeActivity.actionStart(ExamListActivity.this, exam.getId());
                }
            });
        }
    }

    /**
     * 用于显示正在加载的对话框
     * @param operation
     */
    @UiThread
    void showProgressDialog(boolean operation) {
        if(operation == true) progressDialog.show();
        else progressDialog.cancel();
    }
    void init() {
        resideMenu = new ResideMenuGeneral(ExamListActivity.this, ExamListActivity.this).getResideMenu();
        accessManager = new AccessManager(ExamListActivity.this, webService);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
    }

    void afterInit() {
        loadExamList();
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
