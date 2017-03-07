package com.example.administrator.bestojapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestojapp.R;

import com.example.administrator.bestojapp.manager.AccessManager;
import com.example.administrator.bestojapp.web.WebService;

import database.book.Book;
import database.problem.Problem;
import com.special.ResideMenu.ResideMenu;

import junit.framework.Test;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
//import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.rest.*;

import java.util.List;

/**
 * Created by Administrator on 2/28 028.
 */
//@EActivity(R.layout.book_tab)
@EActivity(R.layout.fragment_intro)
public class BookActivity extends AppCompatActivity {

//    @ViewById(R.id.tabs)
//    PagerSlidingTabStrip tabs;
//
//    @ViewById(R.id.pager)
//    ViewPager viewPager;

    @ViewById(R.id.textView_book_intro)
    TextView textView_book_title;

//    @ViewById(R.id.textView_book_author)
//    TextView textView_book_author;

    @ViewById(R.id.textView_book_intro)
    TextView textView_book_intro;

    @RestService
    WebService webService;

    AccessManager accessManager;

    private int currentColor;
    private Long bookId;
    private Book book;

    public static void actionStart(Context context, Long bookId) {
        Intent intent = new Intent(context, BookActivity_.class);
        intent.putExtra("bookId", bookId);
        context.startActivity(intent);
    }

    @UiThread
    void loadUI() {
//        tabs.setViewPager(viewPager);
//        tabs.setIndicatorColor(Color.rgb(63, 81, 181));
//        tabs.setTextColor(Color.rgb(63, 81, 181));
//        textView_book_title.setText(book.getTitle());
//        textView_book_author.setText(book.getAuthor());
    }

    @Background
    public void getBook() {
        book = accessManager.getBookById(bookId.toString());
        Log.d("BookActivity:init", "getBookById address:" + book);
        loadUI();
        Log.d("BookActivity", "getBook:textView_book_title: " + textView_book_title);
    }

    public void init() {
        accessManager = new AccessManager(BookActivity.this, webService);
        bookId = getIntent().getLongExtra("bookId", 1L);
        Log.d("BookActivity:init", bookId.toString());
        getBook();
        Log.d("BookActivity", "init:textView_book_title: " + textView_book_title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
}
