package com.tnninc.writgear.presenter;

import android.os.Bundle;

import com.tnninc.writgear.di.App;
import com.tnninc.writgear.presenter.mappers.TagListMapper;
import com.tnninc.writgear.presenter.vo.Tag;
import com.tnninc.writgear.view.fragment.TagListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TagListPresenter extends BasePresenter {

    @Inject
    protected TagListMapper tagListMapper;

    private TagListView view;

    @Inject
    public TagListPresenter() {
    }

    public TagListPresenter(TagListView view) {
        super();
        this.view = view;
        App.getAppComponent().inject(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        loadTags();
    }

    public void loadTags() {
        Disposable disposable = model.getTagList()
                .map(tagListMapper)
                .subscribe(
                        new Consumer<List<Tag>>() {
                               @Override
                               public void accept(List<Tag> tags) {
                                   view.showData(tags);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                view.showError(throwable.getMessage());
                            }
                        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
