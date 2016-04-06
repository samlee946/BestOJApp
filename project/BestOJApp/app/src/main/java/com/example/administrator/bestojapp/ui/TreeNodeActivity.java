package com.example.administrator.bestojapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.TreeNode2;
import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.database.DatabaseService;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_tree_node)
public class TreeNodeActivity extends AppCompatActivity {

    private Long parentId = 0L;

    private Intent intent;

    private ResideMenu resideMenu;

    private List<TreeNode2> nodes;

    private DatabaseService databaseService;

    @ViewById(R.id.listView_treeNode)
    ListView listView;

    @AfterViews
    void init() {
        listView.setAdapter(new ArrayAdapter<TreeNode2>(TreeNodeActivity.this, android.R.layout.simple_list_item_1, nodes));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeNode2 treeNode2 = (TreeNode2) listView.getItemAtPosition(position);
                if(treeNode2.getType() == 1) {
                    intent = new Intent();
                    intent.setClass(TreeNodeActivity.this, TreeNodeActivity_.class);
                    intent.putExtra("parentId", treeNode2.getId());
                    startActivity(intent);
                }
                else if(treeNode2.getType() == 100) {
                    intent = new Intent();
                    intent.setClass(TreeNodeActivity.this, ProblemActivity_.class);
                    intent.putExtra("problemId", treeNode2.getProblemIdLinked());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentId = getIntent().getLongExtra("parentId", 1099385798655L);

        databaseService = new DatabaseService(TreeNodeActivity.this);

        nodes = databaseService.searchByParentId(parentId);

        resideMenu = new ResideMenuGeneral(TreeNodeActivity.this, TreeNodeActivity.this).getResideMenu();
    }
}
