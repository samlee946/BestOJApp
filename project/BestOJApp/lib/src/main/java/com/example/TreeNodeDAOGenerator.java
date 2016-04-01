package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class TreeNodeDAOGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example");
        addTreeNode(schema);
        new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\Grade3\\GraduateDesign\\BestOJApp\\project\\BestOJApp\\app\\src\\main\\java-gen");
    }

    private static void addTreeNode(Schema schema) {
        Entity treeNode = schema.addEntity("TreeNode2");

        treeNode.addLongProperty("id");
        treeNode.addLongProperty("parentId");
        treeNode.addIntProperty("weight");
        treeNode.addIntProperty("problemIdLinked");
        treeNode.addStringProperty("name");
        treeNode.addIntProperty("type");
    }
}
