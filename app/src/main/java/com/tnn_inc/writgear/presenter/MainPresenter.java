package com.tnn_inc.writgear.presenter;

import android.os.Bundle;
import android.util.Log;

import com.tnn_inc.writgear.model.Model;
import com.tnn_inc.writgear.model.ModelImpl;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.view.fragment.MainView;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter {

    private MainView view;

    private List<Note> noteList;

    public Model model;

    public MainPresenter(MainView view) {
        this.view = view;
        model = new ModelImpl(view.getContext());
    }

    public void onCreate(Bundle savedInstanceState) {

        final Disposable disposable = model.getNoteListRX()
                .subscribe(
                        notes -> view.showData(notes),
                        throwable -> view.showError(throwable.getMessage()));

    }

    //onCreateNoteButtonClick(){}
    //onCreate
    private boolean isNoteListEmpty(){
        return noteList == null || noteList.isEmpty();
    }
}
