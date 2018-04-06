package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.util.Log;
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
import com.tnn_inc.writgear.view.ActivityCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNoteFragment extends BaseFragment implements CreateNoteView {

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
        activityCallback.setFragmentName("CreateNoteFragment");
        return view;
    }

    @Override
    public void onStop() {
        StorIOSQLite storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new DbOpenHelper(getContext()))
                .addTypeMapping(Note.class, new NoteSQLiteTypeMapping())
                .build();

        storIOSQLite
                .put()
                .object(new Note(null, getTitle(), getText(), String.valueOf(System.currentTimeMillis()), null))
                .prepare()
                .executeAsBlocking();

        super.onStop();
    }

    @Override
    public void showError(String msg) {
        makeToast("ERROR! " + msg);
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
