package com.tnninc.writgear.model.database.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.queries.Query;

public class TagsTable {
    @NonNull
    public static final String TABLE = "notes";

    @NonNull
    public static final String COLUMN_ID = "id";

    @NonNull
    public static final String COLUMN_NAME = "name";

    private TagsTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " VARCHAR (255) COLLATE NOCASE"
                + ");";
    }
}
