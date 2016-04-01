package com.example.administrator.bestojapp.database;


import android.app.DownloadManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.DaoMaster;
import com.example.DaoSession;
import com.example.TreeNode2;
import com.example.TreeNode2Dao;
import com.example.TreeNodeDao;
import com.example.administrator.bestojapp.ui.MainActivity;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import java.util.List;


/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class DatabaseService extends MainActivity{

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DatabaseService() {
        setupDatabase();
        getTreeNodeDao();
    }

    private static DatabaseService instance = null;

    public static DatabaseService getInstance() {
        if(instance == null) instance = new DatabaseService();
        return instance;
    }

    public void addTreeNode(Long id, Long parentId, Integer order, Integer problemIdLinked, String name, Integer type) {
        TreeNode2 treeNode = new TreeNode2(id, parentId, order, problemIdLinked, name, type);
        getTreeNodeDao().insert(treeNode);
    }

    public void addTreeNode(TreeNode2 treeNode) {
        getTreeNodeDao().insert(treeNode);
    }

    public List searchByParentId(Long parentId) {
        QueryBuilder queryBuilder = getTreeNodeDao().queryBuilder()
                .where(TreeNodeDao.Properties.ParentId.eq(parentId))
                .orderAsc(TreeNodeDao.Properties.Weight);
        List nodes = queryBuilder.list();
        return nodes;
    }

    private TreeNode2Dao getTreeNodeDao() {
        return daoSession.getTreeNode2Dao();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "treeNode-db", null);
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
