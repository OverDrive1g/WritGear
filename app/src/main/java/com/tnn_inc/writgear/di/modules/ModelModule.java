package com.tnn_inc.writgear.di.modules;

import android.content.Context;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.tnn_inc.writgear.model.database.DbOpenHelper;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.model.database.entities.NoteSQLiteTypeMapping;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    private StorIOSQLite storIOSQLite;

    public ModelModule(Context context) {
        this.storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new DbOpenHelper(context))
                .addTypeMapping(Note.class, new NoteSQLiteTypeMapping())
                .build();
    }

    @Singleton
    @Provides
    StorIOSQLite provideStorIOSQLite(){
        return storIOSQLite;
    }
}
