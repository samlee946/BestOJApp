package com.example.administrator.bestojapp.Bean;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class SolutionDetailJavaBean {
    private Long id;
    private Long problemId;
    private int result;
    private int runningMemory;
    private int runningTime;
    private int echo;
    private String detail;
    private String source;
    private String submitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getRunningMemory() {
        return runningMemory;
    }

    public void setRunningMemory(int runningMemory) {
        this.runningMemory = runningMemory;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
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
}
