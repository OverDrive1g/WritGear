package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.dto.Note;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.view.ActivityCallback;
import com.tnn_inc.writgear.view.adapters.NoteItemAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;
import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;


public class MainFragment extends BaseFragment implements MainView {

    private static String TAG = "MainFragment";

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.note_recycler)
    RecyclerView recyclerView;

    NoteItemAdapter noteItemAdapter;

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

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        noteItemAdapter = new NoteItemAdapter(noteList);
        recyclerView.setAdapter(noteItemAdapter);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation);

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, LEFT | RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchhelper.attachToRecyclerView(recyclerView);

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
        for (int i = 0; i < 40; i++) {
            result.add(new Note(i, "Заметка "+i, "текст заметки адин два три четыре чепырка", new Date()));
        }
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
