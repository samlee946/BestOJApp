package com.example.administrator.bestojapp.Bean;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class SolutionList {
    private Long id;
    private int result;
    private String submitTime;
    private Long problemId;
    private int runningMemory;
    private int type;
    private int runningTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public int getRunningMemory() {
        return runningMemory;
    }

    public void setRunningMemory(int runningMemory) {
        this.runningMemory = runningMemory;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public SolutionList(Long id, int result, String submitTime, Long problemId, int runningMemory, int type, int runningTime) {
        this.id = id;
        this.result = result;
        this.submitTime = submitTime;
        this.problemId = problemId;
        this.runningMemory = runningMemory;
        this.type = type;
        this.runningTime = runningTime;
    }

    public String getResultString() {
        String ret = "";
        switch (result) {
            case 0: ret = "通过"; break;
            case 2: ret = "超时"; break;
            case 3: ret = "超内存"; break;
            case 4: ret = "错误"; break;
            case 5: ret = "运行时错误"; break;
            case 7: ret = "编译错误"; break;
        }
        return ret;
    }

    @Override
    public String toString() {
        return  "提交时间:" + submitTime + " " +
                "评判结果:" + getResultString() + " " +
                "运行时间" + runningTime + "MS " +
                "运行内存" + runningMemory + "KB ";
    }
}

