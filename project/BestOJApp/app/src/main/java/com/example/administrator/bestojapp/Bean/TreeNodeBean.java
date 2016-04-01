package com.example.administrator.bestojapp.Bean;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class TreeNodeBean {

    private Long id;
    private Long parentId;
    private Integer order;
    private Integer problemIdLinked;
    private String name;
    private Integer type;

    public TreeNodeBean(Long id, Long parentId, Integer order, Integer problemIdLinked, String name, Integer type) {
        this.id = id;
        this.parentId = parentId;
        this.order = order;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProblemIdLinked() {
        return problemIdLinked;
    }

    public void setProblemIdLinked(Integer problemIdLinked) {
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
}
