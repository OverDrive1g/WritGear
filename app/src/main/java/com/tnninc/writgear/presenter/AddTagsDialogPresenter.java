
package com.tnninc.writgear.presenter;

import android.os.Bundle;
import android.util.Log;

import com.tnninc.writgear.di.App;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.presenter.mappers.TagListMapper;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.presenter.vo.Tag;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.fragment.AddTagDialogView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class AddTagsDialogPresenter extends BasePresenter {

    private AddTagDialogView view;
    private Note note;

    @Inject
    protected TagListMapper tagListMapper;

    @Inject
    public AddTagsDialogPresenter(){}

    public AddTagsDialogPresenter(AddTagDialogView view, Note note) {
        this.view = view;
        this.note = note;

        App.getAppComponent().inject(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        loadTags();
    }

    public void loadTags(){
        Disposable disposable = model.getTagList()
                .map(tagListMapper)
                .subscribe(
                        tags -> view.showData(tags),
                        throwable -> Log.d("AddTagsDialogPresenter", throwable.getMessage()));

        addDisposable(disposable);
    }

    public void addTag(String tagName) {
        model.putTag(new TagDTO(null, tagName))
                .subscribe(putResult -> view.tagListUpdated(),
                           throwable -> view.showError(throwable.getMessage()));
    }

    public void setTagsForNote(List<Tag> tags) {
        List<Tag> oldTags = note.getTags();
        List<Tag> newTags = new ArrayList<>();
        newTags.addAll(tags);
        newTags.addAll(oldTags);

        note.setTags(newTags);
    }
}
