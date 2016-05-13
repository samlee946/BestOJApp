package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import database.exam.solution.Solution;


@EActivity(R.layout.activity_solution_detail)
public class SolutionDetailActivity extends AppCompatActivity {

    private AccessManager accessManager;

    private Long solutionId;

    private Solution solution;

    private ResideMenu resideMenu;

    @RestService
    WebService webService;

    @ViewById(R.id.textView_detail_problemId)
    TextView listViewProblemID;

    @ViewById(R.id.textView_detail_problem_title)
    TextView listViewProblemTitle;

    @ViewById(R.id.textView_running_time)
    TextView listViewRunningTime;

    @ViewById(R.id.textView_running_memory)
    TextView listViewRunningMemory;

    @ViewById(R.id.textView_judgement_result)
    TextView listViewResult;

    @ViewById(R.id.textView_submit_time)
    TextView listViewSubmitTime;

    @ViewById(R.id.textView_detail)
    TextView listViewDetail;

    @ViewById(R.id.textView_detail_code)
    TextView listViewCode;

    @AfterViews
    public void init() {
        this.solutionId = getIntent().getLongExtra("solutionId", 1L);
        getBySolutionId(solutionId);
        while(accessManager.getEcho() == -1) ;
        if(accessManager.getEcho() == 0) {
            solution = accessManager.getSolution();

            listViewProblemID.setText("题号:" + solution.getProblemId());
            listViewProblemTitle.setText("");
            listViewRunningTime.setText(solution.getRunningTime()+"MS");
            listViewRunningMemory.setText(solution.getRunningMemory()+"KB7");
            listViewSubmitTime.setText(solution.getSubmitTime());
            listViewResult.setText(solution.getResultString());
            listViewDetail.setText(solution.getDetail());
            listViewCode.setText(solution.getSource());
        }
    }

    @Background
    public void getBySolutionId(Long solutionId) {
        accessManager.getBySolutionId(solutionId);
    }

    public static void actionStart(Context context, Long solutionId) {
        Intent intent = new Intent(context, SolutionDetailActivity_.class);
        intent.putExtra("solutionId", solutionId);
        context.startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accessManager = new AccessManager(SolutionDetailActivity.this, webService);

        resideMenu = new ResideMenuGeneral(SolutionDetailActivity.this, SolutionDetailActivity.this).getResideMenu();
    }
}
