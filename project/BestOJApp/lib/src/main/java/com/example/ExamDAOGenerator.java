package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/6 0006.
 * 用于生成考试实体的dao
 */
public class ExamDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.exam");
        addExam(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addExam(Schema schema) {
        Entity exam = schema.addEntity("Exam");

        exam.addLongProperty("id").primaryKey();
        exam.addLongProperty("examId");
        exam.addStringProperty("title");
        exam.addIntProperty("tries");
        exam.addStringProperty("description");
        exam.addStringProperty("startTime");
        exam.addIntProperty("last");
        exam.addDoubleProperty("score");
    }

    /*

    （1）	id	long		试卷的ID
    （2）	examId  long  考试的ID
    （3）	title String 考试的名称
    （4）	try  int第几次考试
    （5）	description 	String	考试的描述
    （6）	startTime	String	考试开始时间
    （7）	last	int	考试持续时间
    （8）	score double 试卷得分


     */
}
