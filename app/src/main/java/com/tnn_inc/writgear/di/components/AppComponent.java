package com.tnn_inc.writgear.di.components;

import com.tnn_inc.writgear.di.modules.ModelModule;
import com.tnn_inc.writgear.di.modules.PresenterModule;
import com.tnn_inc.writgear.model.ModelImpl;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.presenter.NoteListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(ModelImpl dataRepository);
    void inject(BasePresenter presenter);
    void inject(NoteListPresenter presenter);
}
