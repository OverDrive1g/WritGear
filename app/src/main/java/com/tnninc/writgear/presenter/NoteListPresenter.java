package com.tnninc.writgear.presenter;

import android.os.Bundle;
import android.util.Log;

import com.tnninc.writgear.di.App;
import com.tnninc.writgear.presenter.mappers.NoteListMapper;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.fragment.NoteListView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class NoteListPresenter extends BasePresenter {

    @Inject
    protected NoteListMapper noteListMapper;

    private NoteListView view;

    private ActivityCallback activityCallback;

    @Inject
    public NoteListPresenter(){}

    public NoteListPresenter(NoteListView view, ActivityCallback activityCallback) {
        super();
        this.view = view;
        this.activityCallback = activityCallback;
        App.getAppComponent().inject(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        loadNotes();
    }

    public void loadNotes(){
        view.refreshLayoutOn();
        Disposable disposable = model.getNoteList()
                .map(noteListMapper)
                .subscribe(
                        notes -> {view.showData(notes); view.refreshLayoutOff();},
                        throwable -> {view.showError(throwable.getMessage()); view.refreshLayoutOff();});

        addDisposable(disposable);
    }

    public void deleteNoteById(int id){
        model.deleteNoteById(id).subscribe(
                () -> Log.d("NoteListPresenter", "Запись " + id +" удалена!"),
                throwable -> view.showError(throwable.getMessage()));

    }

    public void clickNote(Note note){
        activityCallback.startNoteCreateFragment(note);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
