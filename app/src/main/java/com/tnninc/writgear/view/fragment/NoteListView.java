package com.tnninc.writgear.view.fragment;

import android.content.Context;

import com.tnninc.writgear.presenter.vo.Note;

import java.util.List;

public interface NoteListView extends View {
    void showData(List<Note> noteList);
    Context getContext();
    void showEmptyList();
    void updateNoteItemAdapterOnItemRemove(int adapterPosition);
    void refreshLayoutOn();
    void refreshLayoutOff();
}
