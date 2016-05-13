package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SolutionListDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "database.exam.solution.list");
        addSolutionList(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addSolutionList(Schema schema) {
        Entity examPaper = schema.addEntity("SolutionList");
        examPaper.addLongProperty("id").primaryKey();
        examPaper.addIntProperty("result");
        examPaper.addStringProperty("submitTime");
        examPaper.addLongProperty("problemId");
        examPaper.addLongProperty("examProblemId");
        examPaper.addIntProperty("runningMemory");
        examPaper.addIntProperty("type");
        examPaper.addIntProperty("runningTime");
    }
}

/*

    private Long id;
    private int result;
    private String submitTime;
    private Long problemId;
    private Long examProblemId;
    private int runningMemory;
    private int type;
    private int runningTime;

 */