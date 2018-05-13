package com.tnninc.writgear.presenter;

import android.os.Bundle;
import android.util.Log;

import com.tnninc.writgear.di.App;
import com.tnninc.writgear.presenter.mappers.NoteListMapper;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.fragment.NoteListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class NoteListPresenter extends BasePresenter {

    @Inject
    protected NoteListMapper noteListMapper;

    private NoteListView view;

    private ActivityCallback activityCallback;

    @Inject
    public NoteListPresenter() {
    }

    public NoteListPresenter(NoteListView view, ActivityCallback activityCallback) {
        super();
        this.view = view;
        this.activityCallback = activityCallback;
        App.getAppComponent().inject(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        loadNotes();
    }

    public void loadNotes() {
        view.refreshLayoutOn();
        Disposable disposable = model.getNoteList()
                .map(noteListMapper)
                .subscribe(
                        new Consumer<List<Note>>() {
                            @Override
                            public void accept(List<Note> notes) {
                                view.showData(notes);
                                view.refreshLayoutOff();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Log.e("NoteListPresenter", "loadNotes: "+throwable.getMessage(), throwable);
                                view.refreshLayoutOff();
                            }
                        });

        addDisposable(disposable);
    }

    public void deleteNoteById(int id) {
        Disposable disposable =
                model.deleteNoteById(id).subscribe(
                        new Action() {
                            @Override
                            public void run() {
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Log.e("NoteListPresenter", "deleteNoteById: "+throwable.getMessage(), throwable);
                                view.showError(throwable.getMessage());
                            }
                        });
        addDisposable(disposable);

    }

    public void clickNote(Note note) {
        activityCallback.startNoteCreateFragment(note);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
