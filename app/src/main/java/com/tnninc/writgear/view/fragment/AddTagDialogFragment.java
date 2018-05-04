package com.tnninc.writgear.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.view.adapters.TagItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTagDialogFragment extends DialogFragment {

    @BindView(R.id.dialog_title)
    TextView title;

    @BindView(R.id.ok_btn)
    Button ok;

    @BindView(R.id.cancel_btn)
    Button cancel;

    @BindView(R.id.tag_edit_text)
    EditText editText;

    @BindView(R.id.tag_list)
    ListView tagList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog, null);

        ButterKnife.bind(this, view);

        final List<TagDTO> tags = new ArrayList<>();
        tags.add(new TagDTO(1, "Tag 1"));
        tags.add(new TagDTO(2, "Tag 2"));
        tags.add(new TagDTO(3, "Tag 3"));
        tags.add(new TagDTO(4, "Tag 4"));
        tags.add(new TagDTO(5, "Tag 5"));
        tags.add(new TagDTO(6, "Tag 6"));

        TagItemAdapter tagItemAdapter = new TagItemAdapter(getActivity().getBaseContext(), tags);
        tagList.setAdapter(tagItemAdapter);

        return view;
    }
}
