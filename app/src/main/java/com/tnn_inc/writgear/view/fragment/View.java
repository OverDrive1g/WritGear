package com.tnn_inc.writgear.view.fragment;

import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;

public interface View {
    void showMessage(String msg);
    void showError(String msg);
    void disposeOnStop(@NonNull Disposable disposable);
}
