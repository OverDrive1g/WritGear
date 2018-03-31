package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.EditText;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.view.ActivityCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNoteFragment extends BaseFragment implements CreateNoteView {

    @BindView(R.id.note_text)
    EditText mainEditText;

    public static CreateNoteFragment newInstance() {
        return new CreateNoteFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_note_fragment, null);

        ButterKnife.bind(this, view);
        activityCallback.setFragmentName("CreateNoteFragment");

        return view;
    }

    @Override
    public void showError(String msg) {
        makeToast("ERROR! " + msg);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
