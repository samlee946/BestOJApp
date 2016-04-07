package com.example.administrator.bestojapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_solution_list_by_problem)
public class SolutionListActivity extends AppCompatActivity {

    private int type; //type=1为查看某题 type=2为查看最近
    private Long id;

    private SolutionList[] solutionLists = null;

    private OJService ojService;

    private ResideMenu resideMenu;

    @RestService
    WebService webService;

    @ViewById(R.id.listView_Solution_List)
    ListView listViewSolution;

    public static void actionStart(Context context, int type, Long id) {
        Intent intent = new Intent(context, SolutionListActivity_.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @AfterViews
    public void init() {
        this.type = getIntent().getIntExtra("type", 1);
        this.id = getIntent().getLongExtra("id", 1);
        getListOfUserByProblemId(id);
        while(ojService.getEcho() == -1) ;
        solutionLists = ojService.getSolutionLists();
        //Toast.makeText(SolutionListActivity.this, ojService.getResponse(), Toast.LENGTH_SHORT).show();
        if(ojService.getEcho() == 0) {
            ArrayAdapter<SolutionList> adapter = new ArrayAdapter<SolutionList>(SolutionListActivity.this, android.R.layout.simple_list_item_1);
            for(SolutionList solutionList : solutionLists) {
                adapter.add(solutionList);
            }
            listViewSolution.setAdapter(adapter);
            listViewSolution.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SolutionList solutionList = (SolutionList) listViewSolution.getItemAtPosition(position);
                    SolutionDetailActivity.actionStart(SolutionListActivity.this, solutionList.getId());
                }
            });
        }
        else if(ojService.getEcho() == 2) {
            Toast.makeText(SolutionListActivity.this, "没有找到本题的提交记录", Toast.LENGTH_SHORT).show();
        }
    }

    @Background
    public void getListOfUserByProblemId(Long problemId) {
        ojService.getListOfUserByProblemId(problemId);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ojService = new OJService(SolutionListActivity.this, webService);

        resideMenu = new ResideMenuGeneral(SolutionListActivity.this, SolutionListActivity.this).getResideMenu();
    }
}
