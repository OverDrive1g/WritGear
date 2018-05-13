package com.tnninc.writgear.view.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.presenter.vo.Tag;

import java.util.List;

public class TagsItemAdapter extends BaseAdapter {
    private List<Tag> tags;
    private LayoutInflater layoutInflater;
    private Resources resources;

    public TagsItemAdapter(Context context, List<Tag> tags) {
        this.tags = tags;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resources = context.getResources();
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public Object getItem(int i) {
        return tags.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.tags_list_item, viewGroup, false);
            holder = new ViewHolder();

            holder.title = view.findViewById(R.id.tag_title);
            holder.count = view.findViewById(R.id.note_count_by_tag);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        final Tag tag = tags.get(i);

        holder.title.setText(tag.getName());

        String countNotes = tag.getCountNotes() + " " +
                resources.getString(tag.getCountNotes() > 1 ? R.string.notes : R.string.noteId);

        holder.count.setText(countNotes);

        return view;
    }

    class ViewHolder {
        TextView title;
        TextView count;
    }
}
