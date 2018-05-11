
package com.tnninc.writgear.presenter;

import android.os.Bundle;
import android.util.Log;

import com.tnninc.writgear.di.App;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.presenter.mappers.TagListMapper;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.presenter.vo.Tag;
import com.tnninc.writgear.view.fragment.AddTagDialogView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class AddTagsDialogPresenter extends BasePresenter {

    private AddTagDialogView view;
    private Note note;

    @Inject
    protected TagListMapper tagListMapper;

    @Inject
    public AddTagsDialogPresenter() {
    }

    public AddTagsDialogPresenter(AddTagDialogView view, Note note) {
        this.view = view;
        this.note = note;

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
                                Log.e("AddTagsDialogPresenter", throwable.getMessage());
                            }
                        });

        addDisposable(disposable);
    }

    public void addTag(String tagName) {
        Disposable disposable = model.putTag(new TagDTO(null, tagName))
                .subscribe(
                        new Action() {
                            @Override
                            public void run() {
                                view.tagListUpdated();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                view.showError(throwable.getMessage());
                            }
                        });
        this.addDisposable(disposable);
    }

    public void setTagsForNote(List<Tag> tags) {
        List<Tag> oldTags = note.getTags();
        List<Tag> newTags = new ArrayList<>();
        newTags.addAll(tags);
        newTags.addAll(oldTags);

        note.setTags(newTags);
    }
}
