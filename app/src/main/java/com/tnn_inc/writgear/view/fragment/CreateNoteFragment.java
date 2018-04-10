package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.*;
import android.view.View;
import android.widget.EditText;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.database.DbOpenHelper;
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.model.database.entities.NoteSQLiteTypeMapping;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.presenter.CreateNotePresenter;
import com.tnn_inc.writgear.view.ActivityCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class CreateNoteFragment extends BaseFragment implements CreateNoteView {
    CreateNotePresenter presenter;
    @BindView(R.id.note_text)
    EditText mainEditText;

    @BindView(R.id.note_title)
    EditText titleEditText;

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
        presenter = new CreateNotePresenter(this);
        activityCallback.setFragmentName("CreateNoteFragment");
        return view;
    }

    @Override
    public void onStop() {
        presenter.createNote();
        super.onStop();
    }

    @Override
    public void showError(String msg) {
        makeToast("ERROR! " + msg);
    }

    @Override
    public void disposeOnStop(@NonNull Disposable disposable) {
        // TODO: 09.04.2018
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public String getTitle() {
        return titleEditText.getText().toString();
    }

    @Override
    public String getText() {
        return mainEditText.getText().toString();
    }
}
