package com.example.administrator.bestojapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.bestojapp.R;
import com.example.administrator.bestojapp.api.OJService;
import com.example.administrator.bestojapp.api.WebService;

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

    private Integer echo;

    private Long parentIdExperiment = 1099509530623L;
    private Long parentIdDataStructure = 1099385798655L;
    private Long parentIdAdvancedProgram = 1099347001343L;

    private String response = null;
    private String token = "6fa590b6ccad27feee1eaf4206ed0beb497936af";
    private String jsonString = null;

    OJService ojService;

    Intent intent = null;

    @ViewById(R.id.button_homepage_login)
    Button button_login;

    @ViewById(R.id.button_homepage_data_structure)
    Button button_data_structure;

    @RestService
    WebService webService;

    @Click({R.id.button_homepage_login,
            R.id.button_homepage_experiment,
            R.id.button_homepage_data_structure,
            R.id.button_homepage_download_data,
            R.id.button_homepage_advanced_program,
            R.id.button_homepage_delete_treenode})
    void buttonOnClicked(View view) {
        switch (view.getId()) {
            case R.id.button_homepage_login: {
                intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity_.class);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_experiment: {
                if(!ojService.isTreeNodeDownloaded(parentIdExperiment)) downloadTreeNode(parentIdExperiment);
                while(!ojService.isTreeNodeDownloaded(parentIdExperiment)) ;
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentIdExperiment);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_data_structure: {
                if(!ojService.isTreeNodeDownloaded(parentIdDataStructure)) downloadTreeNode(parentIdDataStructure);
                while(!ojService.isTreeNodeDownloaded(parentIdDataStructure)) ;
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentIdDataStructure);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_advanced_program: {
                if(!ojService.isTreeNodeDownloaded(parentIdAdvancedProgram)) downloadTreeNode(parentIdAdvancedProgram);
                while(!ojService.isTreeNodeDownloaded(parentIdAdvancedProgram)) ;
                intent = new Intent();
                intent.setClass(MainActivity.this, TreeNodeActivity_.class);
                intent.putExtra("parentId", parentIdAdvancedProgram);
                startActivity(intent);
                break;
            }
            case R.id.button_homepage_delete_treenode: {
                ojService.deleteTreeNode();
                break;
            }
            case R.id.button_homepage_download_data: {

                break;
            }
        }
    }

    @Background
    public void downloadTreeNode(Long parentId) {
        ojService.getTreeNodeByParentID(parentId);
    }

    @Background
    public void getTreeNode(Long parentId) {
        try {
            ojService.getTreeNodeByParentID(parentId);
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

        ojService = new OJService(MainActivity.this, webService);
    }
}
