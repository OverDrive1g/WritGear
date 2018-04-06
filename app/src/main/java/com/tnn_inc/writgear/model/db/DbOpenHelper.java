package com.tnn_inc.writgear.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.tnn_inc.writgear.model.db.tables.NotesTable;


public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "writgear_db";

    public DbOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(NotesTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
