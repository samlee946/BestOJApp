package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/4/6 0006.
 */
public class ProblemDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.problem");
        addProblem(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addProblem(Schema schema) {
        Entity problem = schema.addEntity("Problem");

        problem.addLongProperty("id").primaryKey();
        problem.addStringProperty("title");
        problem.addLongProperty("timeLimit");
        problem.addLongProperty("memoryLimit");
        problem.addStringProperty("description");
        problem.addStringProperty("input");
        problem.addStringProperty("output");
        problem.addStringProperty("sampleInput");
        problem.addStringProperty("sampleOutput");
        problem.addStringProperty("source");
        problem.addStringProperty("tip");

        /*
        id	long
        title	String
        timeLimit	long
        memoryLimit	long
        description	String
        input	String
        output	String
        sampleInput	String
        sampleOutput	String
        source	String
        tip	String
         */
    }
}
