package com.tnn_inc.writgear.model;

import com.tnn_inc.writgear.model.db.DatabaseInterface;
import com.tnn_inc.writgear.model.db.entities.Note;

import java.util.List;

import io.reactivex.Observable;

public class ModelImpl implements Model {

    DatabaseInterface databaseInterface;

    @Override
    public Observable<List<Note>> getNotes() {
        return null;
    }

    @Override
    public Observable<List<Note>> getNoteByTag(String tag) {
        return null;
    }

    @Override
    public Observable<Note> getNoteById() {
        return null;
    }
}
