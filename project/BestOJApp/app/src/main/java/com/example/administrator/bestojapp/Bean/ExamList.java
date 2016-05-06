package com.example.administrator.bestojapp.Bean;

import com.exam.Exam;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/5/6 0006.
 */
public class ExamList {
    @SerializedName("notes")
    private Exam[] exams;
    private int echo;

    public Exam[] getExams() {
        return exams;
    }

    public void setExams(Exam[] exams) {
        this.exams = exams;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }
}
