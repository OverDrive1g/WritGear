package com.tnn_inc.writgear.presenter;

import com.tnn_inc.writgear.model.Model;
import com.tnn_inc.writgear.model.ModelImpl;

public abstract class BasePresenter implements Presenter {
    protected Model model = new ModelImpl();

    @Override
    public void onStop() {
    }
}

