package com.tnn_inc.writgear.presenter;

import android.os.Bundle;
import android.util.Log;

import com.tnn_inc.writgear.model.ModelImpl;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.view.fragment.MainView;


import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;

public class MainPresenter extends BasePresenter {

    private MainView view;

    private List<Note> noteList;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState) {

        noteList = new ModelImpl(view.getContext()).getNoteList();
        view.showData(noteList);

    }

    //onCreateNoteButtonClick(){}
    //onCreate
    private boolean isNoteListEmpty(){
        return noteList == null || noteList.isEmpty();
    }
}
