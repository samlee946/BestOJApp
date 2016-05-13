package com.example.administrator.bestojapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.administrator.bestojapp.R;

import java.util.List;

import database.exam.discuss.Discuss;

public class DiscussAdapter extends ArrayAdapter<Discuss> {

    private int resourceId;

    public DiscussAdapter(Context context, int textViewResourceId, List<Discuss> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Discuss discuss = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView discuss_item_username  = (TextView) view.findViewById(R.id.discuss_item_username);
        TextView discuss_item_title     = (TextView) view.findViewById(R.id.discuss_item_title);
        TextView discuss_item_content   = (TextView) view.findViewById(R.id.discuss_item_content);
        discuss_item_username.setText("" + discuss.getUserID());
        discuss_item_title.setText(discuss.getTitle());
        discuss_item_content.setText(discuss.getContent());
        return view;
    }
}
