package com.tnninc.writgear.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tnninc.writgear.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTagDialogFragment extends DialogFragment {

    @BindView(R.id.dialog_title)
    TextView title;

    @BindView(R.id.btnYes)
    Button yes;

    @BindView(R.id.btnNo)
    Button no;

    @BindView(R.id.btnMaybe)
    Button maybe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View view = inflater.inflate(R.layout.dialog, null);

        ButterKnife.bind(this, view);
        return view;
    }
}
