package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.database.entities.NoteDTO;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.presenter.CreateNotePresenter;
import com.tnn_inc.writgear.presenter.vo.Note;
import com.tnn_inc.writgear.view.ActivityCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class CreateNoteFragment extends BaseFragment implements CreateNoteView {
    private static final String BUNDLE_NOTE_KEY = "BUNDLE_NOTE_KEY";

    CreateNotePresenter presenter;
    @BindView(R.id.note_text)
    EditText mainEditText;

    @BindView(R.id.note_title)
    EditText titleEditText;

    Note note;

    public static CreateNoteFragment newInstance(@Nullable Note note) {
        CreateNoteFragment fragment = new CreateNoteFragment();

        if(note != null){
            Bundle args = new Bundle();
            args.putSerializable(BUNDLE_NOTE_KEY, new Note(note.getTitle(), note.getText()));
            fragment.setArguments(args);
        }

        return fragment;
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

    private Note getNoteVO(){
        Bundle bundle = getArguments();
        return bundle != null? (Note) bundle.getSerializable(BUNDLE_NOTE_KEY):null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_note_fragment, null);

        ButterKnife.bind(this, view);
        presenter = new CreateNotePresenter(this);
        note = getNoteVO();
        if(note != null)
            setViewData(note);

        activityCallback.setFragmentName("CreateNoteFragment");
        return view;
    }

    private void setViewData(Note note){
        this.mainEditText.setText(note.getText());
        this.titleEditText.setText(note.getTitle());
    }

    @Override
    public void onStop() {
        presenter.createNote();
        super.onStop();
    }

    @Override
    public void showMessage(String msg) {
        makeToast(msg);
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
        return presenter;
    }

    @Override
    public String getTitle() {
        return titleEditText.getText().toString();
    }

    @Override
    public String getText() {
        return mainEditText.getText().toString();
    }

    @Override
    public Note getNote() {
        return this.note;
    }
}
