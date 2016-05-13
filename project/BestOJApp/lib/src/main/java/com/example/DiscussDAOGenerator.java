package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class DiscussDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "database.exam.discuss");
        addDiscuss(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addDiscuss(Schema schema) {
        Entity examPaper = schema.addEntity("Discuss");
        examPaper.addLongProperty("id").primaryKey();
        examPaper.addIntProperty("userID");
        examPaper.addLongProperty("problemId");
        examPaper.addStringProperty("title");
        examPaper.addStringProperty("content");
        examPaper.addStringProperty("postTime");
    }

}
/*
    private int id;
    private int userID;
    private Long problemId;
    private String title;
    private String content;
    private String postTime;

 */