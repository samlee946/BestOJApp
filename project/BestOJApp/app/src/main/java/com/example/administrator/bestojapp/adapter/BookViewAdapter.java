package com.example.administrator.bestojapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2/28 028.
 */

public class BookViewAdapter extends PagerAdapter {
    private List<View> bookViewList;

    public BookViewAdapter(List<View> mViewList) {
        this.bookViewList = mViewList;
    }

    @Override
    public int getCount() {//必须实现
        return bookViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {//必须实现
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
        container.addView(bookViewList.get(position));
        return bookViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
        container.removeView(bookViewList.get(position));
    }
}
