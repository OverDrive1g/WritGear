package com.tnn_inc.writgear.model;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.tnn_inc.writgear.di.App;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.model.database.tables.NotesTable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ModelImpl implements Model {

    @Inject
    protected StorIOSQLite storIOSQLite;

    public ModelImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Flowable<List<Note>> getNoteList() {
        return storIOSQLite
                .get()
                .listOfObjects(Note.class)
                .withQuery(NotesTable.QUERY_ALL)
                .prepare()
                .asRxFlowable(BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteNoteById(int id) {
        return storIOSQLite
                .delete()
                .byQuery(DeleteQuery.builder()
                .table(NotesTable.TABLE)
                .where(NotesTable.COLUMN_ID + " = ?")
                .whereArgs(id)
                .build())
                .prepare()
                .asRxCompletable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable putNote(Note note) {
        return storIOSQLite
                .put()
                .object(note)
                .prepare()
                .asRxCompletable()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
