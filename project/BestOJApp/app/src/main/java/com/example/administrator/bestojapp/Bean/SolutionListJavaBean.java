package com.example.administrator.bestojapp.Bean;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class SolutionListJavaBean {
    private database.exam.solution.list.SolutionList[] notes;
    private int echo;

    public database.exam.solution.list.SolutionList[] getNotes() {
        return notes;
    }

    public void setNotes(database.exam.solution.list.SolutionList[] notes) {
        this.notes = notes;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }
}
