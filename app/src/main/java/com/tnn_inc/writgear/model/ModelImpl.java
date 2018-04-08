package com.tnn_inc.writgear.model;

import android.content.Context;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.tnn_inc.writgear.model.database.DbOpenHelper;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.model.database.entities.NoteSQLiteTypeMapping;
import com.tnn_inc.writgear.model.database.tables.NotesTable;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ModelImpl implements Model {

    private StorIOSQLite storIOSQLite;

    public ModelImpl(Context context) {
        this.storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new DbOpenHelper(context))
                .addTypeMapping(Note.class, new NoteSQLiteTypeMapping())
                .build();
    }

    @Override
    public List<Note> getNoteList() {
        return storIOSQLite
                .get()
                .listOfObjects(Note.class)
                .withQuery(NotesTable.QUERY_ALL)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public Flowable<List<Note>> getNoteListRX() {
        return storIOSQLite
                .get()
                .listOfObjects(Note.class)
                .withQuery(NotesTable.QUERY_ALL)
                .prepare()
                .asRxFlowable(BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
