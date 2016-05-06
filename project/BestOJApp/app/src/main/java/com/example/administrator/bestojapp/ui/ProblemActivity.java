package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.bestojapp.Bean.SolutionList;
import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;
import com.example.administrator.bestojapp.database.DatabaseServiceProblem;
import com.problem.Problem;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

@EActivity(R.layout.activity_problem)
public class ProblemActivity extends AppCompatActivity {

    private Long problemId;

    private Long paperId;

    private DatabaseServiceProblem databaseServiceProblem;

    private List<Problem> problems;

    private OJService ojService;

    private ResideMenu resideMenu;

    /** type=1时表示是从考试进入的*/
    private int type = -1;

    @RestService
    WebService webService;

    @ViewById
    Button buttonSolutionList;

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

    @Click({R.id.button_solution_list,
            R.id.button_discuss})
    void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.button_solution_list: {
                if(type == 1) SolutionListActivity.actionStart(ProblemActivity.this, 4, problemId, paperId);
                else SolutionListActivity.actionStart(ProblemActivity.this, 1, problemId, 0L);
                break;
            }
            case R.id.button_discuss: {
                DiscussActivity.actionStart(ProblemActivity.this, problemId);
                break;
            }
        }
    }

    public static void actionStart(Context context, Problem problem, Long paperId, Long problemId) {
        Intent intent = new Intent(context, ProblemActivity_.class);
        intent.putExtra("problem", problem);
        intent.putExtra("type", 1);
        intent.putExtra("paperId", paperId);
        intent.putExtra("problemId", problemId);
        context.startActivity(intent);
    }

    @AfterViews
    void init() {
        if(type == -1) {
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
                textViewProblemDescription.setText(Html.fromHtml(problem.getDescription()));
                textViewProblemInput.setText(Html.fromHtml(problem.getInput()));
                textViewProblemOutput.setText(Html.fromHtml(problem.getOutput()));
                textViewProblemSampleInput.setText(Html.fromHtml(problem.getSampleInput()));
                textViewProblemSampleOutput.setText(Html.fromHtml(problem.getSampleOutput()));
                textViewProblemSource.setText(Html.fromHtml(problem.getSource()));
                textViewProblemTip.setText(Html.fromHtml(problem.getTip()));
            }
        }
        else if(type == 1) {
            Problem problem = (Problem) getIntent().getSerializableExtra("problem");
            textViewProblemID.setText("" + problem.getId());
            textViewProblemTitle.setText(problem.getTitle());
            textViewTimeLimit.setText("" + problem.getTimeLimit());
            textViewMemoryLimit.setText("" + problem.getMemoryLimit());
            textViewProblemDescription.setText(Html.fromHtml(problem.getDescription()));
            textViewProblemInput.setText(Html.fromHtml(problem.getInput()));
            textViewProblemOutput.setText(Html.fromHtml(problem.getOutput()));
            textViewProblemSampleInput.setText(Html.fromHtml(problem.getSampleInput()));
            textViewProblemSampleOutput.setText(Html.fromHtml(problem.getSampleOutput()));
            textViewProblemSource.setText(Html.fromHtml(problem.getSource()));
            textViewProblemTip.setText(Html.fromHtml(problem.getTip()));
            problemId = problem.getId();
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

        type = getIntent().getIntExtra("type", -1);

        paperId = getIntent().getLongExtra("paperId", 1L);

        Log.d("Solution", "********" + problemId + " " + type + " " + paperId);

        databaseServiceProblem = new DatabaseServiceProblem(ProblemActivity.this);

        ojService = new OJService(ProblemActivity.this, webService);

        resideMenu = new ResideMenuGeneral(ProblemActivity.this, ProblemActivity.this).getResideMenu();
    }
}
