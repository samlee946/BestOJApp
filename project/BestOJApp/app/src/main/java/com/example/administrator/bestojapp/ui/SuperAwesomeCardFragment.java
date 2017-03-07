package com.example.administrator.bestojapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bestojapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class SuperAwesomeCardFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    @ViewById(R.id.textView)
    TextView textView;

    private int position;

    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @AfterViews
    void loadUI() {
        textView.setText("CARD " + position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card,container,false);
//        loadUI();
        textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("CARD " + position);
        Log.d("Awesome", "onCreateView: " + rootView.findViewById(R.id.textView));
        return rootView;
    }
}