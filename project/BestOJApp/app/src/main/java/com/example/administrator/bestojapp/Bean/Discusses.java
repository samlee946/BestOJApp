package com.example.administrator.bestojapp.Bean;

import database.exam.discuss.Discuss;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class Discusses {
    private Discuss[] discusses;
    private int echo;

    public Discuss[] getDiscusses() {
        return discusses;
    }

    public void setDiscuss(Discuss[] discusses) {
        this.discusses = discusses;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }
}
