package com.tnn_inc.writgear.model.dto;

import java.util.Date;

public class Note {
    private Integer id;
    private String title;
    private String note;
    private Date createDate;

    public Note(Integer id, String title, String note, Date createDate) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.createDate = createDate;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
