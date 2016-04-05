package com.example;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.TreeNode2;

import com.example.TreeNode2Dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig treeNode2DaoConfig;

    private final TreeNode2Dao treeNode2Dao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        treeNode2DaoConfig = daoConfigMap.get(TreeNode2Dao.class).clone();
        treeNode2DaoConfig.initIdentityScope(type);

        treeNode2Dao = new TreeNode2Dao(treeNode2DaoConfig, this);

        registerDao(TreeNode2.class, treeNode2Dao);
    }
    
    public void clear() {
        treeNode2DaoConfig.getIdentityScope().clear();
    }

    public TreeNode2Dao getTreeNode2Dao() {
        return treeNode2Dao;
    }

}