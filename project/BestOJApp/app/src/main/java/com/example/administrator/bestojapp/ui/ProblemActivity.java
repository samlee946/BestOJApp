package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.administrator.bestojapp.R;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;

import database.problem.Problem;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

@EActivity(R.layout.activity_problem)
public class ProblemActivity extends AppCompatActivity {

    private Long problemId;

    private Long paperId;

    private List<Problem> problems;

    private AccessManager accessManager;

    private ResideMenu resideMenu;

    ProgressDialog progressDialog;

    /** type=1时表示是从考试进入的*/
    private int type = -1;

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

    /**
     * 显示题目
     */
    @UiThread
    void showProblem() {
        boolean flag = false;
        if(type == -1) {
            for(Problem problem : problems) {
                if(problem.getId() != null) flag = true;
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
            if(problem.getId() != null) flag = true;
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
        if(!flag) {
            toastShort(getString(R.string.fail_loading_problem));
            this.finish();
        }
    }

    @UiThread
    void toastShort(String msg) {
        Toast.makeText(ProblemActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 读取并显示题目
     */
    @Background
    void loadProblem() {
        showProgressDialog(true);
        problems = accessManager.getProblemByProblemId(problemId);
        showProblem();
        showProgressDialog(false);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    void init() {
        problemId = getIntent().getLongExtra("problemId", 1001L);
        type = getIntent().getIntExtra("type", -1);
        paperId = getIntent().getLongExtra("paperId", 1L);

        Log.d("ProblemActivity", "********" + problemId + " " + type + " " + paperId);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        accessManager = new AccessManager(ProblemActivity.this, webService);

        resideMenu = new ResideMenuGeneral(ProblemActivity.this, ProblemActivity.this).getResideMenu();
    }

    void afterInit() {
        loadProblem();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        afterInit();
    }
}
