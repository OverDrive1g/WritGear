package com.tnn_inc.writgear.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.tnn_inc.writgear.model.db.resolvers.NoteDeleteResolver;
import com.tnn_inc.writgear.model.db.resolvers.NoteGetResolver;
import com.tnn_inc.writgear.model.db.resolvers.NotePutResolver;
import com.tnn_inc.writgear.model.db.entities.Note;

public class DatabaseImpl implements DatabaseInterface {

    @Override
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(Note.class, SQLiteTypeMapping.<Note>builder()
                        .putResolver(new NotePutResolver())
                        .getResolver(new NoteGetResolver())
                        .deleteResolver(new NoteDeleteResolver())
                        .build())
                .build();
    }

    @Override
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}
