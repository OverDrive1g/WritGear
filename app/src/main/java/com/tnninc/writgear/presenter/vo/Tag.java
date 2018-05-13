package com.tnninc.writgear.presenter.vo;

import com.tnninc.writgear.model.database.entities.TagDTO;

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable {
    private Long id;
    private String name;
    private Integer countNotes;

    public Tag(Long id, String name, Integer countNotes) {
        this.id = id;
        this.name = name;
        this.countNotes = countNotes;
    }

    public Tag(TagDTO tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.countNotes = tag.getNotes().size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountNotes() {
        return countNotes;
    }

    public void setCountNotes(Integer countNotes) {
        this.countNotes = countNotes;
    }

    @Override
    public boolean equals(Object obj) {
        Tag t = (Tag) obj;
        return Objects.equals(t.name, name);
    }
}
