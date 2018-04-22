package com.tnninc.writgear.view.fragment;

import com.tnninc.writgear.presenter.vo.Note;

public interface CreateNoteView extends View {
    String getTitle();
    String getText();
    Note getNote();
}
