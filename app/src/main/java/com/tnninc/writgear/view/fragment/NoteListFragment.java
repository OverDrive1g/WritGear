package com.tnninc.writgear.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.tnninc.writgear.R;
import com.tnninc.writgear.presenter.BasePresenter;
import com.tnninc.writgear.presenter.NoteListPresenter;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.adapters.NoteItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;


public class NoteListFragment extends BaseFragment implements NoteListView {
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.note_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    NoteListPresenter presenter;

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
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNotes();
            }
        });

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
                noteItemAdapter.deleteItem(viewHolder.getAdapterPosition());
                updateNoteItemAdapterOnItemRemove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchhelper.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityCallback.startCreateNote();
            }
        });

        presenter = new NoteListPresenter(this, activityCallback);
        presenter.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                activityCallback.openDrawer();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void refreshLayoutOn() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshLayoutOff() {
        refreshLayout.setRefreshing(false);
    }
}
