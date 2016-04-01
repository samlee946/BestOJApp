package com.example.administrator.bestojapp.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.DaoMaster;
import com.example.DaoSession;
import com.example.TreeNode;
import com.example.TreeNode2;
import com.example.TreeNode2Dao;
import com.example.TreeNodeDao;
import com.example.administrator.bestojapp.Bean.TreeNodeBean;
import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJTreeNodeJavaBean;
import com.example.administrator.bestojapp.api.WebService;
import com.example.administrator.bestojapp.database.DatabaseService;
import com.google.gson.Gson;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private int echo;
    private Long parentId = 1099385798655L;

    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";
    private String jsonString = null;

    Intent intent = null;

    //DatabaseService databaseService;

    @ViewById(R.id.button_homepage_login)
    Button button_login;

    @ViewById(R.id.button_homepage_data_structure)
    Button button_data_structure;

    @RestService
    WebService webService;

    @Click({R.id.button_homepage_login, R.id.button_homepage_experiment, R.id.button_homepage_data_structure, R.id.button_homepage_download_data})
    void buttonOnClicked(View view) {
        switch (view.getId()) {
            case R.id.button_homepage_login: {
                intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity_.class);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_experiment: {
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentId);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_data_structure: {
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentId);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_download_data: {
                getTreeNode();
                break;
            }
        }
    }

    @Background
    public void getTreeNode() {
        setButtonDataStructureEnable(false);
        try {
            response = webService.getOffspringByParentId(token, parentId);
            //toastShort(token + " " + parentId);
            Gson gson = new Gson();
            OJTreeNodeJavaBean ojTreeNodeJavaBean = gson.fromJson(response, OJTreeNodeJavaBean.class);
            echo = ojTreeNodeJavaBean.getEcho();
            dealWithEcho(echo);
            if(echo == 0) {
                TreeNodeBean[] treeNodes = ojTreeNodeJavaBean.getNotes();
                toastShort(response);
                for(TreeNodeBean treeNode : treeNodes) {
                    //System.out.println(treeNode.toString());
                    //databaseService.addTreeNode(treeNode);
                    TreeNode2 treeNodeForDB = new TreeNode2(
                            treeNode.getId(),
                            treeNode.getParentId(),
                            treeNode.getOrder(),
                            treeNode.getProblemIdLinked(),
                            treeNode.getName(),
                            treeNode.getType()
                    );
                    getTreeNodeDao().insert(treeNodeForDB);
                }
            }
        } catch (HttpClientErrorException e) {
            toastShort("网络连接错误!原因可能是:" + e.getMessage());
        } catch (ResourceAccessException e) {
            toastShort("网络连接错误!原因可能是:" + e.getMessage());
        } catch (HttpServerErrorException e) {
            toastShort("网络连接错误!原因可能是:" + e.getMessage());
        } finally {
            setButtonDataStructureEnable(true);
        }
    }

    @UiThread
    void setButtonDataStructureEnable(boolean flag) {
        button_data_structure.setEnabled(flag);
    }

    @UiThread
    public void dealWithEcho(int echo) {
        switch (echo) {
            case 0: {
                break;
            }
            case 1: {
                toastShort("参数错误");
                break;
            }
            case 2: {
                toastShort("返回结果为NULL");
                break;
            }
            case 3: {
                toastShort("Token错误");
                break;
            }
            case 4: {
                toastShort("权限不足");
                break;
            }
        }
    }

    @UiThread
    public void toastShort(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void toastLong(String textString) {
        Toast.makeText(this, textString, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //databaseService = DatabaseService.getInstance();
        //databaseService = new DatabaseService();
        setupDatabase();
        getTreeNodeDao();
    }

    //////database
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "treeNode2-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private TreeNode2Dao getTreeNodeDao() {
        return daoSession.getTreeNode2Dao();
    }
    //////
}
