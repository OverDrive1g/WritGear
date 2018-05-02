package com.tnninc.writgear.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tnninc.writgear.model.database.tables.GroupsTable;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;
import com.tnninc.writgear.model.database.tables.NotesTable;


public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "writgear_db";

    public DbOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(NotesTable.getCreateTableQuery());
        db.execSQL(GroupsTable.getCreateTableQuery());
        db.execSQL(NoteTagRelationTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1 && newVersion == 2){
            db.execSQL(NotesTable.getCreateTableQuery());
            db.execSQL(NoteTagRelationTable.getCreateTableQuery());
        }
    }
}
