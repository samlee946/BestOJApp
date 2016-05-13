package com.example.administrator.bestojapp.Bean;

import java.util.Arrays;

import database.example.TreeNode2;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class OJTreeNodeJavaBean {
    private int echo;
    private TreeNode2[] notes;

    public OJTreeNodeJavaBean(int echo, TreeNode2[] notes) {
        this.echo = echo;
        this.notes = notes;
    }

    public TreeNode2[] getNotes() {

        return notes;
    }

    public void setNotes(TreeNode2[] notes) {
        this.notes = notes;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }

    @Override
    public String toString() {
        return "OJTreeNodeJavaBean{" +
                "echo=" + echo +
                ", notes=" + Arrays.toString(notes) +
                '}';
    }
}
