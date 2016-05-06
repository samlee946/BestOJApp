package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.exam.Exam;
import com.example.TreeNode2;
import com.example.administrator.bestojapp.Bean.ExamPaper;
import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;
import com.example.administrator.bestojapp.impl.ExamAdapter;
import com.problem.Problem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_exam_node)
public class ExamNodeActivity extends AppCompatActivity {

    /** 考试详情 */
    ExamPaper examPaper;

    /** 考试的id */
    private Long examPaperId;

    /** 考试的题目列表 */
    private List<Problem> problemList = new ArrayList<Problem>();

    private OJService ojService;

    @RestService
    WebService webService;

    @ViewById(R.id.listView_exam_problems)
    ListView listView;

    @ViewById(R.id.textView_exam_title)
    TextView textView_exam_title;

    @ViewById(R.id.textView_exam_begin_time)
    TextView textView_exam_begin_time;

    @ViewById(R.id.textView_exam_last_time)
    TextView textView_exam_last_time;

    @ViewById(R.id.textView_exam_description)
    TextView textView_exam_description;

    @ViewById(R.id.button_exam_node_solution)
    Button button_exam_node_solution;

    public static void actionStart(Context context, Long examPaperId) {
        Intent intent = new Intent(context, ExamNodeActivity_.class);
        intent.putExtra("examPaperId", examPaperId);
        context.startActivity(intent);
    }

    @Click({R.id.button_exam_node_solution})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_exam_node_solution: {
                SolutionListActivity.actionStart(ExamNodeActivity.this, 3, 0L, examPaperId);
                break;
            }
        }
    }

    @AfterViews
    void init() {
        examPaperId = getIntent().getLongExtra("examPaperId", 0L);
        getExamNode();
        while(ojService.getEcho() == -1) ;
        examPaper = ojService.getExamPaper();
        if(ojService.getEcho() == 0) {
            textView_exam_title.setText(examPaper.getTitle());
            textView_exam_begin_time.setText(examPaper.getStartTime());
            textView_exam_last_time.setText("" + examPaper.getLast() + "分钟");
            textView_exam_description.setText(examPaper.getDescription());
            for(Problem problem : examPaper.getProblems()) {
                problemList.add(problem);
            }
            ArrayAdapter adapter = new ArrayAdapter<>(ExamNodeActivity.this, android.R.layout.simple_list_item_1, problemList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Problem problem = (Problem) listView.getItemAtPosition(position);
                    ProblemActivity.actionStart(ExamNodeActivity.this, problem, examPaperId, problem.getId());
                }
            });
        }
    }

    @Background
    void getExamNode() {
        ojService.getExamPaperFromServer(examPaperId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ojService = new OJService(ExamNodeActivity.this, webService);
    }
}
