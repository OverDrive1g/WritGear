package com.tnn_inc.writgear.model.db.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.queries.Query;

public class NotesTable {
    @NonNull
    public static final String TABLE = "tweets";

    @NonNull
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_GROUP_ID = "group_id";
    public static final String COLUMN_CREATE_DATA = "create_data";

    private NotesTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COLUMN_TITLE + " VARCHAR (255) COLLATE NOCASE, "
                + COLUMN_TEXT + " TEXT COLLATE NOCASE,"
                + COLUMN_GROUP_ID + " INTEGER REFERENCES groups (id),"
                + COLUMN_CREATE_DATA + " DATETIME NOT NULL"
                + ");";
    }
}
