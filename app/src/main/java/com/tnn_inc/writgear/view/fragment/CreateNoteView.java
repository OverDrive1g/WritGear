package com.tnn_inc.writgear.view.fragment;

import com.tnn_inc.writgear.presenter.mappers.vo.Note;

public interface CreateNoteView extends View {
    String getTitle();
    String getText();
    Note getNote();
}
