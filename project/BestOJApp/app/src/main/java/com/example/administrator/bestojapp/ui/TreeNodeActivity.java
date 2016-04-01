package com.example.administrator.bestojapp.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.DaoMaster;
import com.example.DaoSession;
import com.example.TreeNode;
import com.example.TreeNode2;
import com.example.TreeNode2Dao;
import com.example.TreeNodeDao;
import com.example.administrator.bestojapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

@EActivity(R.layout.activity_tree_node)
public class TreeNodeActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private Cursor cursor;

    private Long parentId = 0L;

    @ViewById(R.id.listView_treeNode)
    ListView listView;

    @AfterViews
    void init() {

    }

    public List searchByParentId(Long parentId) {
        QueryBuilder queryBuilder = getTreeNodeDao().queryBuilder()
                .where(TreeNodeDao.Properties.ParentId.eq(parentId))
                .orderAsc(TreeNodeDao.Properties.Weight);
        List nodes = queryBuilder.list();
        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);
        return nodes;
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "treeNode2-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private TreeNode2Dao getTreeNodeDao() {
        return daoSession.getTreeNode2Dao();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentId = getIntent().getLongExtra("parentId", 1099385798655L);
        setupDatabase();

        String orderColumn = TreeNodeDao.Properties.Weight.columnName;
        //String orderBy = orderColumn;
//        cursor = db.query(getTreeNodeDao().getTablename(), getTreeNodeDao().getAllColumns(), null, null, null, null, null);
//        String[] from = {TreeNodeDao.Properties.Name.columnName};
//        int[] to = {android.R.id.text1};
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from,
//                to);
//        listView.setAdapter(adapter);
    }
}
