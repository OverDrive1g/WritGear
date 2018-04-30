package com.tnninc.writgear.model.database.tables;

import android.support.annotation.NonNull;

public class NoteTagRelationTable {
    @NonNull
    public static final String TABLE = "note_to_cars";

    @NonNull
    public static final String COLUMN_ID = "note_to_cars";

    @NonNull
    public static final String COLUMN_NOTE_ID = "note_to_cars";

    @NonNull
    public static final String COLUMN_TAG_ID = "note_to_cars";

    private NoteTagRelationTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_NOTE_ID + " INTEGER NOT NULL,"
                + COLUMN_TAG_ID + " INTEGER NOT NULL"
                + ");";
    }
}
