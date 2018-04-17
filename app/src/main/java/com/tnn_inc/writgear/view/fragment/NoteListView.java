package com.tnn_inc.writgear.view.fragment;

import android.content.Context;

import com.tnn_inc.writgear.model.database.entities.NoteDTO;
import com.tnn_inc.writgear.presenter.vo.Note;

import java.util.List;

public interface NoteListView extends View {
    void showData(List<Note> noteList);
    Context getContext();
    void showEmptyList();
    void updateNoteItemAdapterOnItemRemove(int adapterPosition);
}
