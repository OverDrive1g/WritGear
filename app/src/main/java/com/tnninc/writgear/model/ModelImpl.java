package com.tnninc.writgear.model;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.tnninc.writgear.di.App;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.tables.NotesTable;

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
    public Flowable<List<NoteDTO>> getNoteList() {
        return storIOSQLite
                .get()
                .listOfObjects(NoteDTO.class)
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
    public Completable putNote(NoteDTO note) {
        return storIOSQLite
                .put()
                .object(note)
                .prepare()
                .asRxCompletable()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
