package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.dto.Note;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.view.ActivityCallback;
import com.tnn_inc.writgear.view.adapters.NoteListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends BaseFragment implements MainView {

    private static String TAG = "MainFragment";

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.note_list)
    ListView noteList;

    NoteListAdapter noteListAdapter;

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
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);

        activityCallback.setFragmentName("MainFragment");

        List<Note> noteList = getNotes();

        noteListAdapter = new NoteListAdapter(getContext(), noteList);

        this.noteList.setAdapter(noteListAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityCallback.startCreateNote();
            }
        });
        return view;
    }

    List<Note> getNotes(){
        List<Note> result = new ArrayList<>();
        result.add(new Note(1, "Заметка 1", "текст заметки адин два три четыре чепырка", new Date()));
        result.add(new Note(2, "Заметка 2", "текст заметки адин два три четыре чепырка", new Date()));
        result.add(new Note(3, "Заметка 3", "текст заметки адин два три четыре чепырка", new Date()));
        result.add(new Note(4, "Заметка 4", "текст заметки адин два три четыре чепырка", new Date()));
        result.add(new Note(5, "Заметка 5", "текст заметки адин два три четыре чепырка", new Date()));
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
