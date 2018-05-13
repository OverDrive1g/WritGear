package com.tnninc.writgear.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.presenter.BasePresenter;
import com.tnninc.writgear.presenter.TagListPresenter;
import com.tnninc.writgear.presenter.vo.Tag;
import com.tnninc.writgear.view.adapters.TagsItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagListFragment extends BaseFragment implements TagListView{

    @BindView(R.id.tag_list1)
    ListView tagList;
    TagsItemAdapter adapter;
    TagListPresenter presenter;

    public TagListFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_list_layout, null);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        presenter = new TagListPresenter(this);
        presenter.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void showData(List<Tag> tags) {
        adapter = new TagsItemAdapter(this.getActivity(), tags);
        tagList.setAdapter(adapter);

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showError(String msg) {

    }
}
