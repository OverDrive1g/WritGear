package com.tnn_inc.writgear.presenter;

import android.os.Bundle;

import com.tnn_inc.writgear.di.App;
import com.tnn_inc.writgear.presenter.mappers.NoteListMapper;
import com.tnn_inc.writgear.presenter.vo.Note;
import com.tnn_inc.writgear.view.ActivityCallback;
import com.tnn_inc.writgear.view.fragment.NoteListView;

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
        Disposable disposable = model.getNoteList()
                .map(noteListMapper)
                .subscribe(
                        notes -> view.showData(notes),
                        throwable -> view.showError(throwable.getMessage()));

        addDisposable(disposable);
    }

    public void deleteNoteById(int id){
        model.deleteNoteById(id).subscribe(
                () -> view.showError("Запись " + id +" удалена!"),
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
