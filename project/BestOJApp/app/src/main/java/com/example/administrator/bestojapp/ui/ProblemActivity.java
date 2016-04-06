package com.example.administrator.bestojapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;
import com.example.administrator.bestojapp.database.DatabaseServiceProblem;
import com.problem.Problem;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

@EActivity(R.layout.activity_problem)
public class ProblemActivity extends AppCompatActivity {

    private Long problemId;

    private DatabaseServiceProblem databaseServiceProblem;

    private List<Problem> problems;

    private OJService ojService;

    private ResideMenu resideMenu;

    @RestService
    WebService webService;

    @ViewById(R.id.textViewProblemID)
    TextView textViewProblemID;

    @ViewById(R.id.textViewProblemTitle)
    TextView textViewProblemTitle;

    @ViewById(R.id.textViewTimeLimit)
    TextView textViewTimeLimit;

    @ViewById(R.id.textViewMemoryLimit)
    TextView textViewMemoryLimit;

    @ViewById(R.id.textViewSubmitTimes)
    TextView textViewSubmitTimes;

    @ViewById(R.id.textViewACTimes)
    TextView textViewACTimes;

    @ViewById(R.id.textViewProblemDescription)
    TextView textViewProblemDescription;

    @ViewById(R.id.textViewProblemInput)
    TextView textViewProblemInput;

    @ViewById(R.id.textViewProblemOutput)
    TextView textViewProblemOutput;

    @ViewById(R.id.textViewProblemSampleInput)
    TextView textViewProblemSampleInput;

    @ViewById(R.id.textViewProblemSampleOutput)
    TextView textViewProblemSampleOutput;

    @ViewById(R.id.textViewProblemSource)
    TextView textViewProblemSource;

    @ViewById(R.id.textViewProblemTip)
    TextView textViewProblemTip;


    @AfterViews
    void init() {
        if(!ojService.isProblemDownloaded(problemId)) {
            downloadProblem(problemId);
        }
        while(!ojService.isProblemDownloaded(problemId)) ;
        problems = databaseServiceProblem.searchByProblemId(problemId);
        for(Problem problem : problems) {
            textViewProblemID.setText("" + problem.getId());
            textViewProblemTitle.setText(problem.getTitle());
            textViewTimeLimit.setText("" + problem.getTimeLimit());
            textViewMemoryLimit.setText("" + problem.getMemoryLimit());
            textViewProblemDescription.setText(problem.getDescription());
            textViewProblemInput.setText(problem.getInput());
            textViewProblemOutput.setText(problem.getOutput());
            textViewProblemSampleInput.setText(problem.getSampleInput());
            textViewProblemSampleOutput.setText(problem.getSampleOutput());
            textViewProblemSource.setText(problem.getSource());
            textViewProblemTip.setText(problem.getTip());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Background
    void downloadProblem(Long problemId) {
        ojService.getProblemByProblemId(problemId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        problemId = getIntent().getLongExtra("problemId", 1001L);

        databaseServiceProblem = new DatabaseServiceProblem(ProblemActivity.this);

        ojService = new OJService(ProblemActivity.this, webService);

        resideMenu = new ResideMenuGeneral(ProblemActivity.this, ProblemActivity.this).getResideMenu();
    }
}
