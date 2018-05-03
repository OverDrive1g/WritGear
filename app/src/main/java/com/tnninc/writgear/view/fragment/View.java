package com.tnninc.writgear.view.fragment;

import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;

public interface View {
    void showMessage(String msg);
    void showError(String msg);
}
