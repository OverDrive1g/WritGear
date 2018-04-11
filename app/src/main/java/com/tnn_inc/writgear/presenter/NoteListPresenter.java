package com.tnn_inc.writgear.presenter;

import android.os.Bundle;

import com.tnn_inc.writgear.model.ModelImpl;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.view.fragment.NoteListView;


import java.util.List;

import io.reactivex.disposables.Disposable;

public class NoteListPresenter extends BasePresenter {

    private NoteListView view;

    private List<Note> noteList;

    Disposable disposable;

    public NoteListPresenter(NoteListView view) {
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState) {

        dispose();

        disposable = model.getNoteList()
                .subscribe(
                        notes -> view.showData(notes),
                        throwable -> view.showError(throwable.getMessage()));
    }

    public void deleteNoteById(int id){
        model.deleteNoteById(id).subscribe(
                () -> {},
                throwable -> view.showError(throwable.getMessage()));
    }

    public void clickNote(Note note){
        view.startEditNoteFragment(note);
    }

    private void dispose(){
        if( disposable != null){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }

    @Override
    public void onStop() {
        dispose();
    }
}
