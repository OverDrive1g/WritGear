package com.tnninc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tnninc.writgear.R;
import com.tnninc.writgear.presenter.BasePresenter;
import com.tnninc.writgear.presenter.CreateNotePresenter;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.ActivityCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateNoteFragment extends BaseFragment implements CreateNoteView {
    private static final String BUNDLE_NOTE_KEY = "BUNDLE_NOTE_KEY";

    CreateNotePresenter presenter;

    @BindView(R.id.create_note_layout)
    LinearLayout createNoteLayout;

    @BindView(R.id.note_text)
    EditText mainEditText;

    @BindView(R.id.note_title)
    EditText titleEditText;

    Note note;

    AddTagDialogFragment dialog;

    public CreateNoteFragment() {
        setHasOptionsMenu(true);
    }

    public static CreateNoteFragment newInstance(@Nullable Note note) {
        CreateNoteFragment fragment = new CreateNoteFragment();

        if(note != null){
            Bundle args = new Bundle();
            args.putSerializable(BUNDLE_NOTE_KEY, note);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_note_fragment, null);

        ButterKnife.bind(this, view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        dialog = new AddTagDialogFragment();
        presenter = new CreateNotePresenter(this, activityCallback);
        note = getNoteVO();
        if(note != null)
            setViewData(note);

        return view;
    }

    private Note getNoteVO(){
        Bundle bundle = getArguments();
        return bundle != null? (Note) bundle.getSerializable(BUNDLE_NOTE_KEY):null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_create_note, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.add_tag:
                dialog.show(getFragmentManager(), "dialog");
                return true;
            case R.id.action_save:
                getActivity().onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
