package com.example.administrator.bestojapp.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.bestojapp.R;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;

import database.book.Book;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.*;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.book_tab)
public class BookActivity extends AppCompatActivity {

    @ViewById(R.id.tabs)
    PagerSlidingTabStrip tabs;

    @ViewById(R.id.pager)
    ViewPager viewPager;

    @ViewById(R.id.textView_book_title)
    TextView textView_book_title;

    @ViewById(R.id.textView_book_author)
    TextView textView_book_author;

    @ViewById(R.id.textView_book_intro)
    TextView textView_book_intro;

    @ViewById(R.id.textView_book_content)
    TextView textView_book_content;

    @RestService
    WebService webService;

    private Long bookId;
    private Book book;

    AccessManager accessManager;

    private List<Fragment> fragments = new ArrayList<>();

    public static void actionStart(Context context, Long bookId) {
        Intent intent = new Intent(context, BookActivity_.class);
        intent.putExtra("bookId", bookId);
        context.startActivity(intent);
    }

    /**
     * 显示题目
     */
    @UiThread
    void loadUI() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabs.setViewPager(viewPager);
        tabs.setIndicatorColor(Color.rgb(63, 81, 181));
        tabs.setTextColor(Color.rgb(63, 81, 181));
        viewPager.setCurrentItem(0);
        if(book != null) {
            textView_book_title.setText(book.getTitle());
            textView_book_author.setText(book.getAuthor());
//            textView_book_intro.setText(book.getIntro());
//            textView_book_content.setText(book.getContent());
            Log.d("TestActivity", "showProblem:textView_book_title: " + textView_book_title);
        }
    }

    @Background
    public void showBook() {
        book = accessManager.getBookById(bookId.toString());
        loadUI();
        BookFragment f1 = BookFragment.newFragment(book.getIntro());
        BookFragment f2 = BookFragment.newFragment(book.getContent());
        BookFragment f3 = BookFragment.newFragment(book.getIntro());
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
    }

    public void init() {
        accessManager = new AccessManager(BookActivity.this, webService);
        bookId = getIntent().getLongExtra("bookId", 1L);
        Log.d("TestActivity:init", bookId.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        showBook();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"          简介         ", "          目录         ", "          评价          "};

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
//            return SuperAwesomeCardFragment.newInstance(position);
            return fragments.get(position);
        }
    }
}
