package com.tnninc.writgear.model.database.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteCreator;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import com.tnninc.writgear.model.database.tables.NotesTable;

import java.util.List;

@StorIOSQLiteType(table = NotesTable.TABLE, generateTableClass = false)
public class NoteDTO {

    @Nullable
    @StorIOSQLiteColumn(name = NotesTable.COLUMN_ID, key = true)
    private Integer id;

    @Nullable
    @StorIOSQLiteColumn(name = NotesTable.COLUMN_TITLE)
    private String title;

    @Nullable
    @StorIOSQLiteColumn(name = NotesTable.COLUMN_TEXT)
    private String text;

    @NonNull
    @StorIOSQLiteColumn(name = NotesTable.COLUMN_CREATE_DATA)
    private String createDate;

    @Nullable
    @StorIOSQLiteColumn(name = NotesTable.COLUMN_GROUP_ID)
    private Integer groupId;

    @NonNull
    @StorIOSQLiteColumn(name = NotesTable.COLUMN_COLOR)
    private Integer color;

    @Nullable
    private List<TagDTO> tags;

    private NoteDTO() {
    }

    public NoteDTO(@Nullable Integer id, @Nullable String title, @Nullable String text,
                   @NonNull String createDate, @Nullable Integer groupId, @NonNull Integer color) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createDate = createDate;
        this.groupId = groupId;
        this.color = color;
    }

    public NoteDTO(@Nullable Integer id, @Nullable String title, @Nullable String text,
                   @NonNull String createDate, @Nullable Integer groupId, @NonNull Integer color,
                   @Nullable List<TagDTO> tags) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createDate = createDate;
        this.groupId = groupId;
        this.color = color;
        this.tags = tags;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getText() {
        return text;
    }

    @NonNull
    public String getCreateDate() {
        return createDate;
    }

    @Nullable
    public Integer getGroupId() {
        return groupId;
    }

    @NonNull
    public Integer getColor() {
        return color;
    }

    @Nullable
    public List<TagDTO> getTags() {
        return tags;
    }

    @StorIOSQLiteCreator
    static NoteDTO create(@Nullable Integer id, @Nullable String title, @Nullable String text,
                          @NonNull String createDate, @Nullable Integer groupId, @NonNull Integer color) {
        return new NoteDTO(id, title, text, createDate, groupId, color);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + groupId.hashCode();
        result = 31 * result + createDate.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        NoteDTO note = (NoteDTO) obj;
        if (id != null ? !id.equals(note.id) : note.id != null) return false;

        assert text != null;
        return text.equals(note.text);
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", groupId='" + groupId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

