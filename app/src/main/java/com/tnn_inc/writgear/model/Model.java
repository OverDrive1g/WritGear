package com.tnn_inc.writgear.model;

import com.tnn_inc.writgear.model.db.entities.Note;

import java.util.List;

import io.reactivex.Observable;

public interface Model {
    Observable<List<Note>> getNotes();
    Observable<List<Note>> getNoteByTag(String tag);
    Observable<Note> getNoteById();
}
