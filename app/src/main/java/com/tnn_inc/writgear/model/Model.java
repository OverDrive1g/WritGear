package com.tnn_inc.writgear.model;

import com.tnn_inc.writgear.model.database.entities.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface Model {
    Flowable<List<Note>> getNoteList();
    Completable deleteNoteById(int id);
    Completable putNote(Note note);
}
