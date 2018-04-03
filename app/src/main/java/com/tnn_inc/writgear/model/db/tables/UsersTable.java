package com.tnn_inc.writgear.model.db.tables;

import android.support.annotation.NonNull;

public class UsersTable {
    @NonNull
    public static final String TABLE = "users";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_NICK = "nick";

    @NonNull
    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;

    @NonNull
    public static final String COLUMN_NICK_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_NICK;

    private UsersTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_NICK + " TEXT NOT NULL UNIQUE"
                + ");";
    }
}
