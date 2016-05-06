package com.example.administrator.bestojapp.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exam.Exam;
import com.example.administrator.bestojapp.Bean.DiscussJavaBean;
import com.example.administrator.bestojapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6 0006.
 */
public class ExamAdapter extends ArrayAdapter<Exam> {

    private int resourceId;

    public ExamAdapter(Context context, int textViewResourceId, List<Exam> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Exam exam = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView exam_item_examid       = (TextView) view.findViewById(R.id.exam_item_examid);
        TextView exam_item_title        = (TextView) view.findViewById(R.id.exam_item_title);
        TextView exam_item_begin_time   = (TextView) view.findViewById(R.id.exam_item_begin_time);
        TextView exam_item_last_time     = (TextView) view.findViewById(R.id.exam_item_last_time);
        exam_item_examid.setText("" + exam.getExamId());
        exam_item_title.setText(exam.getTitle());
        exam_item_begin_time.setText(exam.getStartTime());
        exam_item_last_time.setText(exam.getLast() + "分钟");
        return view;
    }
}
