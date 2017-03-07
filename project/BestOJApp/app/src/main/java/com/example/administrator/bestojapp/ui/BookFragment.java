package com.example.administrator.bestojapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bestojapp.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Administrator on 2017-03-07.
 */
@EFragment
public class BookFragment extends Fragment {

    @ViewById(R.id.textView_book_intro)
    TextView textView_intro;

    private static final String arg_str = "str";

    private String str;


    public static BookFragment newFragment(String str) {
        BookFragment f = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(arg_str, str);
        Log.d("getItem: ", "putString" + str);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str = getArguments().getString(arg_str);
        Log.d("getItem: ", "getString" + str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro,container,false);
        textView_intro = (TextView) view.findViewById(R.id.textView_book_intro);
        textView_intro.setText(Html.fromHtml(str));
        return view;
    }
}
