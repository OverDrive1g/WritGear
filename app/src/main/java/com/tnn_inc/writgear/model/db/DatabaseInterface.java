package com.tnn_inc.writgear.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;

public interface DatabaseInterface {
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper);
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context);
}
