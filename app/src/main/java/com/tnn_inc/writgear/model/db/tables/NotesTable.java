package com.tnn_inc.writgear.model.db.tables;

import android.support.annotation.NonNull;

public class NotesTable {
    @NonNull
    public static final String TABLE = "tweets";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_CONTENT = "content";

    @NonNull
    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;

    @NonNull
    public static final String COLUMN_CONTENT_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_CONTENT;

    private NotesTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_CONTENT + " TEXT NOT NULL"
                + ");";
    }
}
