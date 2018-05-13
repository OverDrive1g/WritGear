package com.tnninc.writgear.view.fragment;

import com.tnninc.writgear.presenter.vo.Tag;

import java.util.List;

public interface TagListView extends View {
    void showData(List<Tag> tags);
}
