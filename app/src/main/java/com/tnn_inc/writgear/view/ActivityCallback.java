package com.tnn_inc.writgear.view;

import android.support.v7.app.AppCompatActivity;

public interface ActivityCallback {
    void startCreateNote();
    AppCompatActivity getActivity();
    void setFragmentName(String fragmentName);
}
