package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2017-03-07.
 */
public class BookDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "database.book");
        addBook(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }
    private static void addBook(Schema schema) {
        Entity book = schema.addEntity("Book");
        book.addLongProperty("id").primaryKey();
        book.addStringProperty("title");
        book.addStringProperty("author");
        book.addStringProperty("intro");
        book.addStringProperty("content");
        book.addFloatProperty("rating");
    }
}
//create table `Book` (
//        `id`		int not null auto_increment,
//        `title`		varchar(128),
//        `author`	varchar(128),
//        `intro`		text,
//        `content`	text,
//        `rating`	float,
//        primary key(`id`)
//        );