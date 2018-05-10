package com.tnninc.writgear.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.presenter.AddTagsDialogPresenter;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.presenter.vo.Tag;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.adapters.TagItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTagDialogFragment extends DialogFragment implements AddTagDialogView {
    private static final String BUNDLE_NOTE_KEY = "BUNDLE_NOTE_KEY_ATDF";

    @BindView(R.id.tag_edit_text)
    EditText editText;

    @BindView(R.id.tag_list)
    ListView tagList;

    private AddTagsDialogPresenter presenter;
    TagItemAdapter adapter;
    ActivityCallback activityCallback;

    public AddTagDialogFragment() {
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

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    public static AddTagDialogFragment newInstance(@Nullable Note note) {
        AddTagDialogFragment fragment = new AddTagDialogFragment();

        if (note != null) {
            Bundle args = new Bundle();
            args.putSerializable(BUNDLE_NOTE_KEY, note);
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                .setTitle(R.string.choice_tag_for_note)
                .setPositiveButton(R.string.ok,
                        (dialog, whichButton) -> {
                            presenter.setTagsForNote(new ArrayList<>(adapter.getSelectedTags()));
                            dialog.dismiss();
                        }
                )
                .setNegativeButton(R.string.cancel,
                        (dialog, whichButton) -> dialog.dismiss()
                );

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog, null);

        ButterKnife.bind(this, view);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    return;
                }
                if (start < s.length())
                    if (s.charAt(start) == ',') {
                        String resultTagName = s.subSequence(0, start).toString();
                        if(Objects.equals(resultTagName, ""))
                            return;
                        presenter.addTag(resultTagName);
                        editText.setText("");
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        presenter = new AddTagsDialogPresenter(this, getNoteVO());
        presenter.onCreate(savedInstanceState);
        return b.setView(view).create();
    }

    private Note getNoteVO() {
        Bundle bundle = getArguments();
        return bundle != null ? (Note) bundle.getSerializable(BUNDLE_NOTE_KEY) : null;
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showError(String msg) {
        Log.e("AddTagDialogFragment", msg);
    }

    @Override
    public void tagListUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showData(List<Tag> tags) {
        adapter = new TagItemAdapter(getActivity().getBaseContext(), tags, getNoteVO().getTags());
        tagList.setAdapter(adapter);

    }
}
