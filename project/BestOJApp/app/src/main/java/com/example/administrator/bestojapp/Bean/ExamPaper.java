package com.example.administrator.bestojapp.Bean;

import com.google.gson.annotations.SerializedName;
import com.problem.Problem;

/**
 * Created by Administrator on 2016/5/6 0006.
 */
public class ExamPaper {

    private int echo;
    private Long id;
    private Long examId;
    private String title;
    @SerializedName("try")
    private Integer tries;
    private String description;
    private String startTime;
    private Integer last;
    private Double score;
    @SerializedName("notes")
    private Problem[] problems;

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTries() {
        return tries;
    }

    public void setTries(Integer tries) {
        this.tries = tries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Problem[] getProblems() {
        return problems;
    }

    public void setProblems(Problem[] problems) {
        this.problems = problems;
    }

}
