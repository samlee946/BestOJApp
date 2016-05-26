package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.activity_solution_list_by_problem)
public class SolutionListActivity extends AppCompatActivity {

    private int type; //type=1为查看某题 type=2为查看最近 type=3为查看考试提交 type=4为查看考试某题提交
    private Long id;
    private Long paperId;

    private database.exam.solution.list.SolutionList[] solutionLists = null;

    private AccessManager accessManager;

    private ResideMenu resideMenu;

    ProgressDialog progressDialog;

    @RestService
    WebService webService;

    @ViewById(R.id.listView_Solution_List)
    ListView listViewSolution;

    /**
     *
     * @param context
     * @param type
     * @param id
     */
    public static void actionStart(Context context, int type, Long id, Long paperId) {
        Intent intent = new Intent(context, SolutionListActivity_.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        intent.putExtra("paperId", paperId);
        context.startActivity(intent);
    }

    @UiThread
    public void showSolutionList() {
        ArrayAdapter<database.exam.solution.list.SolutionList> adapter = new ArrayAdapter<database.exam.solution.list.SolutionList>(SolutionListActivity.this, android.R.layout.simple_list_item_1);
        for(database.exam.solution.list.SolutionList solutionList : solutionLists) {
            if(type == 4) {
                Log.d("Solution", solutionList.getId() + " " + solutionList.getExamProblemId() + " " + id);
                if(!solutionList.getExamProblemId().equals(id)) {
                    continue;
                }
            }
            adapter.add(solutionList);
        }
        if(adapter.isEmpty()) {
            Toast.makeText(SolutionListActivity.this, "没有找到本题的提交记录", Toast.LENGTH_SHORT).show();
        }
        else {
            listViewSolution.setAdapter(adapter);
            listViewSolution.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    database.exam.solution.list.SolutionList solutionList = (database.exam.solution.list.SolutionList) listViewSolution.getItemAtPosition(position);
                    SolutionDetailActivity.actionStart(SolutionListActivity.this, solutionList.getId());
                }
            });
        }
    }

    @UiThread
    void toastShort(String msg) {
        Toast.makeText(SolutionListActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    void init() {
        accessManager = new AccessManager(SolutionListActivity.this, webService);
        resideMenu = new ResideMenuGeneral(SolutionListActivity.this, SolutionListActivity.this).getResideMenu();
        this.type = getIntent().getIntExtra("type", 1);
        this.id = getIntent().getLongExtra("id", 1L);
        this.paperId = getIntent().getLongExtra("paperId", 1L);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        Log.d("Solution", type + " " + id + " " + paperId);
    }

    @Background
    void loadSolutionList() {
        showProgressDialog(true);
        solutionLists = accessManager.getListOfUserByProblemId(type, id, paperId);
        showProgressDialog(false);
        if(solutionLists == null || solutionLists.length <= 0) {
            toastShort(getString(R.string.fail_loading_solution_list));
        }
        else {
            showSolutionList();
        }
    }

    void afterinit() {
        loadSolutionList();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        afterinit();
    }
}
