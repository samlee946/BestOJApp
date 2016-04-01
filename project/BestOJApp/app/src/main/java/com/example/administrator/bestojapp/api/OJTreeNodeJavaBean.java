package com.example.administrator.bestojapp.api;

import com.example.TreeNode;
import com.example.administrator.bestojapp.Bean.TreeNodeBean;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class OJTreeNodeJavaBean {
    private int echo;
    private TreeNodeBean[] notes;

    public OJTreeNodeJavaBean(int echo, TreeNodeBean[] notes) {
        this.echo = echo;
        this.notes = notes;
    }

    public TreeNodeBean[] getNotes() {

        return notes;
    }

    public void setNotes(TreeNodeBean[] notes) {
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
