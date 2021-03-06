package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import database.example.TreeNode2;

import com.example.administrator.bestojapp.web.WebService;
import com.example.administrator.bestojapp.manager.AccessManager;

import com.example.administrator.bestojapp.R;
import com.special.ResideMenu.ResideMenu;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.*;

import java.util.List;

@EActivity(R.layout.activity_tree_node)
public class TreeNodeActivity extends AppCompatActivity {

    private Long parentId = 0L;

    private Intent intent;

    private ResideMenu resideMenu;

    private List<TreeNode2> nodes;

    private AccessManager accessManager;

    ProgressDialog progressDialog;

    @ViewById(R.id.listView_treeNode)
    ListView listView;

    @RestService
    WebService webService;

    /**
     * 显示获取到的树结点
     */
    @UiThread
    void showTreeNode() {
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
                    ProblemActivity.actionStart(TreeNodeActivity.this, treeNode2.getProblemIdLinked());
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }


    /**
     * 获取树结点并显示
     */
    @Background
    void loadTreeNode() {
        showProgressDialog(true);
        nodes = accessManager.getTreeNodeByParentID(parentId);
        if(nodes.isEmpty()) {
            toastShort(getString(R.string.fail_loading_tree_node));
            this.finish();
        }
        else {
            showTreeNode();
        }
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

    @UiThread
    void toastShort(String msg) {
        Toast.makeText(TreeNodeActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    void init() {
        parentId = getIntent().getLongExtra("parentId", 1099385798655L);

        accessManager = new AccessManager(TreeNodeActivity.this, webService);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在读取");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        resideMenu = new ResideMenuGeneral(TreeNodeActivity.this, TreeNodeActivity.this).getResideMenu();
    }

    void afterInit() {
        loadTreeNode();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        afterInit();
    }
}
