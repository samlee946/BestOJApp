package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class SolutionDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "database.exam.solution");
        addSolution(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addSolution(Schema schema) {
        Entity examPaper = schema.addEntity("Solution");
        examPaper.addLongProperty("id").primaryKey();
        examPaper.addLongProperty("problemId");
        examPaper.addIntProperty("result");
        examPaper.addIntProperty("runningMemory");
        examPaper.addIntProperty("runningTime");
        examPaper.addIntProperty("echo");
        examPaper.addStringProperty("detail");
        examPaper.addStringProperty("source");
        examPaper.addStringProperty("submitTime");
    }
}

/*

    private Long id;
    private Long problemId;
    private int result;
    private int runningMemory;
    private int runningTime;
    private int echo;
    private String detail;
    private String source;
    private String submitTime;

 */