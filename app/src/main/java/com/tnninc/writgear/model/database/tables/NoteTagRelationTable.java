package com.tnninc.writgear.model.database.tables;

import android.support.annotation.NonNull;

public class NoteTagRelationTable {
    @NonNull
    public static final String TABLE = "notes_to_tags";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_NOTE_ID = "note_id";

    @NonNull
    public static final String COLUMN_TAG_ID = "tag_id";

    private NoteTagRelationTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTE_ID + " INTEGER NOT NULL,"
                + COLUMN_TAG_ID + " INTEGER NOT NULL"
                + ");";
    }
}
