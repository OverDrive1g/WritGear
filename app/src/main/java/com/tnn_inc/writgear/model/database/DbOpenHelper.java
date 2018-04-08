package com.tnn_inc.writgear.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tnn_inc.writgear.model.database.tables.GroupsTable;
import com.tnn_inc.writgear.model.database.tables.NotesTable;


public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "writgear_db";

    public DbOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        Log.d("db create", "onCreate: db");
        db.execSQL(NotesTable.getCreateTableQuery());
        db.execSQL(GroupsTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}