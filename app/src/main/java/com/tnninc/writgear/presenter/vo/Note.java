package com.tnninc.writgear.presenter.vo;

import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.utils.Converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Note implements Serializable {
    private Integer id;
    private String title;
    private String text;
    private String time;
    private Integer color;
    private List<Tag> tags;

    public Note() {
        this.tags = new ArrayList<>();
    }

    public Note(NoteDTO noteDTO){
        super();
        this.id = noteDTO.getId();
        this.title = noteDTO.getTitle();
        this.text = noteDTO.getText();
        this.time = noteDTO.getCreateDate();
        this.color = noteDTO.getColor();
        this.tags = Converter.getTagsFromTagDTOs(noteDTO.getTags());
    }

    public Note(Integer id, String title, String text, String time, Integer color, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
        this.color = color;
        this.tags = tags;
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

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
