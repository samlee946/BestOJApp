package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.bestojapp.Bean.SolutionDetailJavaBean;
import com.example.administrator.bestojapp.Bean.SolutionList;
import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

@EActivity(R.layout.activity_solution_detail)
public class SolutionDetailActivity extends AppCompatActivity {

    private OJService ojService;

    private Long solutionId;

    private SolutionDetailJavaBean solutionDetailJavaBean;

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
        while(ojService.getEcho() == -1) ;
        if(ojService.getEcho() == 0) {
            solutionDetailJavaBean = ojService.getSolutionDetailJavaBean();

            listViewProblemID.setText("题号:" + solutionDetailJavaBean.getProblemId());
            listViewProblemTitle.setText("");
            listViewRunningTime.setText(solutionDetailJavaBean.getRunningTime()+"MS");
            listViewRunningMemory.setText(solutionDetailJavaBean.getRunningMemory()+"KB7");
            listViewSubmitTime.setText(solutionDetailJavaBean.getSubmitTime());
            listViewResult.setText(solutionDetailJavaBean.getResultString());
            listViewDetail.setText(solutionDetailJavaBean.getDetail());
            listViewCode.setText(solutionDetailJavaBean.getSource());
        }
    }

    @Background
    public void getBySolutionId(Long solutionId) {
        ojService.getBySolutionId(solutionId);
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

        ojService = new OJService(SolutionDetailActivity.this, webService);

        resideMenu = new ResideMenuGeneral(SolutionDetailActivity.this, SolutionDetailActivity.this).getResideMenu();
    }
}
