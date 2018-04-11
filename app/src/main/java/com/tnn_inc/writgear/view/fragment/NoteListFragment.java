package com.tnn_inc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.presenter.BasePresenter;
import com.tnn_inc.writgear.presenter.NoteListPresenter;
import com.tnn_inc.writgear.view.ActivityCallback;
import com.tnn_inc.writgear.view.adapters.NoteItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;


public class NoteListFragment extends BaseFragment implements NoteListView {
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.note_recycler)
    RecyclerView recyclerView;

    NoteListPresenter presenter;

    NoteItemAdapter noteItemAdapter;

    @NonNull
    private final CompositeDisposable compositeDisposableForOnStop = new CompositeDisposable();

    @Override
    public void onStop() {
        compositeDisposableForOnStop.clear();
        super.onStop();
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
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);

        activityCallback.setFragmentName("NoteListFragment");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation);


        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, LEFT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteNoteById(noteItemAdapter.getNoteByPosition(viewHolder.getAdapterPosition()).getId());
                updateNoteItemAdapterOnItemRemove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchhelper.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(view1 -> activityCallback.startCreateNote());

        presenter = new NoteListPresenter(this);
        presenter.onCreate(savedInstanceState);
        return view;
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
    public void disposeOnStop(@NonNull Disposable disposable) {
        compositeDisposableForOnStop.add(disposable);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showData(List<Note> noteList) {
        noteItemAdapter = new NoteItemAdapter(noteList, presenter);
        recyclerView.setAdapter(noteItemAdapter);

    }

    @Override
    public void showEmptyList() {
        showError("База пуста");
    }

    @Override
    public void updateNoteItemAdapterOnItemRemove(int adapterPosition) {
        noteItemAdapter.notifyItemRemoved(adapterPosition);
        noteItemAdapter.notifyItemRangeChanged(adapterPosition, noteItemAdapter.getItemCount());
    }

    @Override
    public void startEditNoteFragment(Note note) {
        activityCallback.startNoteCreateFragment(note);
    }
}