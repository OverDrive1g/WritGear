package com.tnn_inc.writgear.view;

import android.support.v7.app.AppCompatActivity;

import com.tnn_inc.writgear.model.database.entities.Note;

public interface ActivityCallback {
    void startCreateNote();
    AppCompatActivity getActivity();
    void startNoteCreateFragment(Note note);
    void setFragmentName(String fragmentName);
}
