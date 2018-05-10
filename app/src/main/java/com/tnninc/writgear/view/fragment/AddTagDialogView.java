package com.tnninc.writgear.view.fragment;

import com.tnninc.writgear.presenter.vo.Tag;

import java.util.List;

public interface AddTagDialogView extends View{
    void tagListUpdated();
    void showData(List<Tag> tags);
}
