package com.example.administrator.bestojapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.bestojapp.Bean.DiscussJavaBean;
import com.example.administrator.bestojapp.R;

import java.util.List;

public class DiscussAdapter extends ArrayAdapter<DiscussJavaBean> {

    private int resourceId;

    public DiscussAdapter(Context context, int textViewResourceId, List<DiscussJavaBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscussJavaBean discussJavaBean = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView discuss_item_username  = (TextView) view.findViewById(R.id.discuss_item_username);
        TextView discuss_item_title     = (TextView) view.findViewById(R.id.discuss_item_title);
        TextView discuss_item_content   = (TextView) view.findViewById(R.id.discuss_item_content);
        discuss_item_username.setText("" + discussJavaBean.getUserID());
        discuss_item_title.setText(discussJavaBean.getTitle());
        discuss_item_content.setText(discussJavaBean.getContent());
        return view;
    }
}
