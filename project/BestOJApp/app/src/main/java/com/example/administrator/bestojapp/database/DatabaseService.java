package com.example.administrator.bestojapp.database;


import android.app.DownloadManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.DaoMaster;
import com.example.DaoSession;
import com.example.TreeNode;
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
    private Context context;

    public DatabaseService(Context context) {
        this.context = context;
        setupDatabase();
        getTreeNode2Dao();
    }
    /*
    public void addTreeNode(Long id, Long parentId, Integer order, Integer problemIdLinked, String name, Integer type) {
        TreeNode2 treeNode = new TreeNode2(id, parentId, order, problemIdLinked, name, type);
        getTreeNode2Dao().insert(treeNode);
    }
    */

    public void addTreeNode(TreeNode2 treeNode) {
        getTreeNode2Dao().insert(treeNode);
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
