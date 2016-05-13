package com.example.administrator.bestojapp.manager;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import database.example.TreeNode2;

import java.util.List;


/**
 * Created by Administrator on 2016/4/1 0001.
 */
public interface TreeNodeManager{

    /**
     * init
     * @param context
     */
    public void init(Context context);

    /**
     * add a TreeNode into local database
     * @param treeNode
     */
    public void addTreeNode(TreeNode2 treeNode);

    /**
     * search TreeNodes in local database by parentId
     * @param parentId
     * @return
     */
    public List<TreeNode2> searchByParentId(Long parentId);

    /**
     * check if a TreeNode is downloaded in local database by parentID
     * @param parentID
     * @return
     */
    public boolean isTreeNodeDownloaded(Long parentID);

    /**
     * dropTable
     */
    public void dropTable();

    /**
     * createTable
     */
    public void createTable();

    /**
     * getDb
     * @return
     */
    public SQLiteDatabase getDb();

    /**
     * setDb
     * @param db
     */
    public void setDb(SQLiteDatabase db);
}
