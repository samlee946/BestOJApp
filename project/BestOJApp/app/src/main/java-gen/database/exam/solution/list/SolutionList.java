package database.exam.solution.list;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SOLUTION_LIST.
 */
public class SolutionList {

    private Long id;
    private Integer result;
    private String submitTime;
    private Long problemId;
    private Long examProblemId;
    private Integer runningMemory;
    private Integer type;
    private Integer runningTime;

    public SolutionList() {
    }

    public SolutionList(Long id) {
        this.id = id;
    }

    public SolutionList(Long id, Integer result, String submitTime, Long problemId, Long examProblemId, Integer runningMemory, Integer type, Integer runningTime) {
        this.id = id;
        this.result = result;
        this.submitTime = submitTime;
        this.problemId = problemId;
        this.examProblemId = examProblemId;
        this.runningMemory = runningMemory;
        this.type = type;
        this.runningTime = runningTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getExamProblemId() {
        return examProblemId;
    }

    public void setExamProblemId(Long examProblemId) {
        this.examProblemId = examProblemId;
    }

    public Integer getRunningMemory() {
        return runningMemory;
    }

    public void setRunningMemory(Integer runningMemory) {
        this.runningMemory = runningMemory;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
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
        return  "题号:" + (problemId == null ? examProblemId : problemId) + " " +
                "提交时间:" + submitTime + " " +
                "评判结果:" + getResultString() + " " +
                "运行时间" + runningTime + "MS " +
                "运行内存" + runningMemory + "KB ";
    }
}
