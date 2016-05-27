package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class MessageDAOGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "database.message");
        addMessage(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addMessage(Schema schema) {
        Entity message = schema.addEntity("Message");

        message.addLongProperty("id");
        message.addStringProperty("date");
        message.addIntProperty("type");
        message.addLongProperty("linkedID");
        message.addStringProperty("extraMsg");
        message.addLongProperty("userID");
    }
}
