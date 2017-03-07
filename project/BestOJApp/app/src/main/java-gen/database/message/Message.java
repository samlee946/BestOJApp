package database.message;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.google.gson.annotations.SerializedName;

/**
 * Entity mapped to table MESSAGE.
 */
public class Message {

    private Long id;
    private String date;
    private Integer type;
    @SerializedName("linkedId")
    private Long linkedID;
    private String extraMsg;
    private Long userID;

    public Message() {
    }

    public Message(Long id, String date, Integer type, Long linkedID, String extraMsg, Long userID) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.linkedID = linkedID;
        this.extraMsg = extraMsg;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getLinkedID() {
        return linkedID;
    }

    public void setLinkedID(Long linkedID) {
        this.linkedID = linkedID;
    }

    public String getExtraMsg() {
        return extraMsg;
    }

    public void setExtraMsg(String extraMsg) {
        this.extraMsg = extraMsg;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return  date + " " +
                extraMsg;
    }
}