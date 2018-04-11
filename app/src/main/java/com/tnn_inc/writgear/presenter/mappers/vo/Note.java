package com.tnn_inc.writgear.presenter.mappers.vo;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer id;
    private String title;
    private String text;
    private String createDate;
    private Integer groupId;

    public Note(Integer id, String title, String text, String createDate, Integer groupId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createDate = createDate;
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
