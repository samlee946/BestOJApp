package database.example;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.google.gson.annotations.SerializedName;

/**
 * Entity mapped to table TREE_NODE2.
 */
public class TreeNode2 {

    private Long id;
    private Long parentId;
    @SerializedName("order")
    private Integer weight;
    private Long problemIdLinked;
    private String name;
    private Integer type;

    public TreeNode2() {
    }

    public TreeNode2(Long id, Long parentId, Integer weight, Long problemIdLinked, String name, Integer type) {
        this.id = id;
        this.parentId = parentId;
        this.weight = weight;
        this.problemIdLinked = problemIdLinked;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getProblemIdLinked() {
        return problemIdLinked;
    }

    public void setProblemIdLinked(Long problemIdLinked) {
        this.problemIdLinked = problemIdLinked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if(type == 1) return name;
        return problemIdLinked + " " + name;
    }
}