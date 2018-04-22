package com.tnninc.writgear.presenter.vo;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer id;
    private String title;
    private String text;
    private String time;


    public Note(Integer id, String title, String text, String time) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
