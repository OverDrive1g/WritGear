package com.tnninc.writgear.presenter;

import com.tnninc.writgear.di.App;
import com.tnninc.writgear.model.Model;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;

    @Inject
    protected CompositeDisposable compositeDisposable;

    public BasePresenter() {
        App.getAppComponent().inject(this);
        compositeDisposable = new CompositeDisposable();
    }

    protected void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
    }
}

