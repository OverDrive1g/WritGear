package com.tnn_inc.writgear.model;

import com.tnn_inc.writgear.model.database.entities.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface Model {
    List<Note> getNoteList();
    Flowable<List<Note>> getNoteListRX();

}
