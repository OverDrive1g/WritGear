package com.tnninc.writgear.view;

import android.support.v7.app.AppCompatActivity;

import com.tnninc.writgear.presenter.vo.Note;

public interface ActivityCallback {
    void startCreateNote();
    AppCompatActivity getActivity();
    void startNoteCreateFragment(Note note);
    void setFragmentName(String fragmentName);
}
