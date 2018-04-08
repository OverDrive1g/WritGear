package com.tnn_inc.writgear.view.fragment;

import android.content.Context;

import com.tnn_inc.writgear.model.database.entities.Note;

import java.util.List;

public interface MainView extends View {
    void showData(List<Note> noteList);
    Context getContext();
    void showEmptyList();
}
