package com.tnn_inc.writgear.presenter;

import com.tnn_inc.writgear.di.App;
import com.tnn_inc.writgear.model.Model;

import javax.inject.Inject;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;

    public BasePresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onStop() {
    }
}

