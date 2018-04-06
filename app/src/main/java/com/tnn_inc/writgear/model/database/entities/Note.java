package com.tnn_inc.writgear.model.database.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteCreator;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import com.tnn_inc.writgear.model.database.tables.NotesTable;

@StorIOSQLiteType(table = NotesTable.TABLE, generateTableClass = false)
public class Note {

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

    private Note() {
    }

    public Note(@Nullable Integer id, @Nullable String title, @Nullable String text,
                @NonNull String createDate, @Nullable Integer groupId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createDate = createDate;
        this.groupId = groupId;
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

    @StorIOSQLiteCreator
    static Note create(@Nullable Integer id, @Nullable String title, @Nullable String text,
                       @NonNull String createDate, @Nullable Integer groupId) {
        return new Note(id, title, text, createDate, groupId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + groupId.hashCode();
        result = 31 * result + createDate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Note note = (Note) obj;
        if (id != null ? !id.equals(note.id) : note.id != null) return false;

        assert text != null;
        return text.equals(note.text);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", groupId='" + groupId + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}

