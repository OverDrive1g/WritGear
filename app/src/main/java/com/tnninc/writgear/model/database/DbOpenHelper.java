package com.tnninc.writgear.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.tnninc.writgear.model.database.tables.GroupsTable;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;
import com.tnninc.writgear.model.database.tables.NotesTable;
import com.tnninc.writgear.model.database.tables.TagsTable;


public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "writgear_db";

    public DbOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, 5);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(NotesTable.getCreateTableQuery());
        db.execSQL(GroupsTable.getCreateTableQuery());
        db.execSQL(TagsTable.getCreateTableQuery());
        db.execSQL(NoteTagRelationTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NotesTable.getCreateTableQuery());
        db.execSQL(GroupsTable.getCreateTableQuery());
        db.execSQL(TagsTable.getCreateTableQuery());
        db.execSQL(NoteTagRelationTable.getCreateTableQuery());
    }
}
