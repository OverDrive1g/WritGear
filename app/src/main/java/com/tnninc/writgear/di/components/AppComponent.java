package com.tnninc.writgear.di.components;

import com.tnninc.writgear.di.modules.ModelModule;
import com.tnninc.writgear.di.modules.PresenterModule;
import com.tnninc.writgear.model.ModelImpl;
import com.tnninc.writgear.presenter.AddTagsDialogPresenter;
import com.tnninc.writgear.presenter.BasePresenter;
import com.tnninc.writgear.presenter.NoteListPresenter;
import com.tnninc.writgear.presenter.TagListPresenter;
import com.tnninc.writgear.view.VoiceHook;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(ModelImpl dataRepository);
    void inject(BasePresenter presenter);
    void inject(NoteListPresenter presenter);
    void inject(AddTagsDialogPresenter presenter);
    void inject(VoiceHook hook);
    void inject(TagListPresenter presenter);
}
