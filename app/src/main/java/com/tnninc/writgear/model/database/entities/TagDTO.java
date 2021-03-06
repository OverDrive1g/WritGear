package com.tnninc.writgear.model.database.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteCreator;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import com.tnninc.writgear.model.database.tables.TagsTable;

import java.util.List;

@StorIOSQLiteType(table = TagsTable.TABLE, generateTableClass = false)
public class TagDTO {
    @Nullable
    @StorIOSQLiteColumn(name = TagsTable.COLUMN_ID, key = true)
    private Long id;

    @NonNull
    @StorIOSQLiteColumn(name = TagsTable.COLUMN_NAME)
    private String name;

    @Nullable
    private List<NoteDTO> notes;

    public TagDTO() {
    }

    public TagDTO(@Nullable Long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public TagDTO(@Nullable Long id, @NonNull String name, @Nullable List<NoteDTO> notes) {
        this(id, name);
        this.notes = notes;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public List<NoteDTO> getNotes() {
        return notes;
    }

    @StorIOSQLiteCreator
    static TagDTO create(@Nullable Long id, @NonNull String name){
        return new TagDTO(id, name);
    }

    @Override
    public String toString() {
        return "Tag{"+
                "id=" + id +
                ", name=" + name +
                ", notes=" + notes +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        TagDTO o = (TagDTO) obj;
        return (o.getName() == this.getName()) || (o.getId() == this.getId());
    }
}
