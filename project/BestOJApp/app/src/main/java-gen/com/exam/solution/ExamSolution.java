package com.exam.solution;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EXAM_SOLUTION.
 */
public class ExamSolution {

    private Long id;
    private Long examProblemId;
    private Integer result;
    private String submitTime;
    private Integer runningTime;
    private Integer runningMemory;

    public ExamSolution() {
    }

    public ExamSolution(Long id) {
        this.id = id;
    }

    public ExamSolution(Long id, Long examProblemId, Integer result, String submitTime, Integer runningTime, Integer runningMemory) {
        this.id = id;
        this.examProblemId = examProblemId;
        this.result = result;
        this.submitTime = submitTime;
        this.runningTime = runningTime;
        this.runningMemory = runningMemory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamProblemId() {
        return examProblemId;
    }

    public void setExamProblemId(Long examProblemId) {
        this.examProblemId = examProblemId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public Integer getRunningMemory() {
        return runningMemory;
    }

    public void setRunningMemory(Integer runningMemory) {
        this.runningMemory = runningMemory;
    }

}
