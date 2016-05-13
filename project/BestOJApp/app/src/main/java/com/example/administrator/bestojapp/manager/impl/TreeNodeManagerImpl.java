package com.example.administrator.bestojapp.manager.impl;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import database.example.DaoMaster;
import database.example.DaoSession;
import database.example.TreeNode2;
import database.example.TreeNode2Dao;
import com.example.administrator.bestojapp.manager.TreeNodeManager;
import de.greenrobot.dao.query.QueryBuilder;


/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class TreeNodeManagerImpl implements TreeNodeManager{

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Context context;

    public void init(Context context) {
        this.context = context;
        setupDatabase();
        getTreeNode2Dao();
    }

    public void addTreeNode(TreeNode2 treeNode) {
        QueryBuilder queryBuilder = getTreeNode2Dao().queryBuilder()
                .where(TreeNode2Dao.Properties.Id.eq(treeNode.getId()))
                .orderAsc(TreeNode2Dao.Properties.Weight);
        if(queryBuilder.list().isEmpty()) {
            getTreeNode2Dao().insert(treeNode);
        }
    }

    public List<TreeNode2> searchByParentId(Long parentId) {
        QueryBuilder queryBuilder = getTreeNode2Dao().queryBuilder()
                .where(TreeNode2Dao.Properties.ParentId.eq(parentId))
                .orderAsc(TreeNode2Dao.Properties.Weight);
        return (List<TreeNode2>) queryBuilder.list();
    }

    public boolean isTreeNodeDownloaded(Long parentID) {
        if(searchByParentId(parentID).isEmpty()) return false;
        else return true;
    }

    public void dropTable() {
        getTreeNode2Dao().dropTable(db, true);
    }

    public void createTable() {
        getTreeNode2Dao().createTable(db, true);
    }

    private TreeNode2Dao getTreeNode2Dao() {
        return daoSession.getTreeNode2Dao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "treeNode2-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }
}
