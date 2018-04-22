package com.tnninc.writgear.view.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.tnninc.writgear.presenter.BasePresenter;
import com.tnninc.writgear.view.ActivityCallback;

public abstract class BaseFragment extends Fragment {

    protected ActivityCallback activityCallback;

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if(getPresenter() != null)
            getPresenter().onStop();
    }

    protected void makeToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}