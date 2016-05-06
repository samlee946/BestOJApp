package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.exam.Exam;
import com.example.administrator.bestojapp.Bean.DiscussJavaBean;
import com.example.administrator.bestojapp.Bean.ExamList;
import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;
import com.example.administrator.bestojapp.impl.DiscussAdapter;
import com.example.administrator.bestojapp.impl.ExamAdapter;
import com.example.administrator.bestojapp.impl.UserManager;
import com.problem.Problem;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
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

    private UserManager userManager;

    private OJService ojService;

    /** 服务器返回的考试列表 */
    private ExamList examList;

    /** 另一种格式的列表... */
    List<Exam> exams = new ArrayList<Exam>();

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ExamListActivity_.class);
        context.startActivity(intent);
    }

    @Background
    void getExamList() {
        ojService.getExamListFromServer();
    }

    @AfterViews
    void init() {
        getExamList();
        while(ojService.getEcho() == -1) ;
        examList = ojService.getExamList();
        if(ojService.getEcho() == 0) {
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resideMenu = new ResideMenuGeneral(ExamListActivity.this, ExamListActivity.this).getResideMenu();
        userManager = UserManager.getInstance();
        ojService = new OJService(ExamListActivity.this, webService);
    }
}
