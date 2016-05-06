package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/6 0006.
 */
public class ExamSolutionDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.exam.solution");
        addExamSolution(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addExamSolution(Schema schema) {
        Entity examSolution = schema.addEntity("ExamSolution");
        examSolution.addLongProperty("id").primaryKey();
        examSolution.addLongProperty("examProblemId");
        examSolution.addIntProperty("result");
        examSolution.addStringProperty("submitTime");
        examSolution.addIntProperty("runningTime");
        examSolution.addIntProperty("runningMemory");
    }
}

/*

（1）	id	long		提交记录的ID
（2）	examProblemId  long  考试题ID
（3）	result	int	此次提交的结果(0-通过；2-超进；3-超内存；4-错误；5-运行时错误；7-编译错)
（4）	submitTime	String	提交时间
（5）	runningTime	int	运行的时间（ms）
（6）	runningMemory	int	运行的内存（KB）


 */