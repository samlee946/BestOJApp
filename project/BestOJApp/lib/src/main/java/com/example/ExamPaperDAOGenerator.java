package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class ExamPaperDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "database.exam.paper");
        addExamList(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addExamList(Schema schema) {
        Entity examPaper = schema.addEntity("ExamPaper");
        examPaper.addLongProperty("id").primaryKey();
        examPaper.addStringProperty("examPaper");
    }
}
