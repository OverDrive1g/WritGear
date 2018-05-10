package com.tnninc.writgear.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.presenter.vo.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Tag> tags;
    private List<Tag> selectedTags;
    private Set<Tag> selectedList;

    public TagItemAdapter(Context context, List<Tag> tags, List<Tag> selectedTags) {
        this.context = context;
        this.tags = tags;
        this.selectedList = new HashSet<>();
        if(selectedTags == null){
            this.selectedTags = new ArrayList<>();
        } else {
            this.selectedTags = selectedTags;
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

        if (view == null){
            view = layoutInflater.inflate(R.layout.tag_list_item, viewGroup, false);

            holder = new ViewHolder();

            holder.layout = view.findViewById(R.id.tag_item);
            holder.text = view.findViewById(R.id.tag_text_view);
            holder.cb = view.findViewById(R.id.tag_check_box);

            view.setTag(holder);
        } else
            holder = (ViewHolder)view.getTag();

        Tag tag = tags.get(i);

        holder.text.setText(tag.getName());
        boolean isContains = selectedTags.contains(tag);
        holder.cb.setChecked(isContains);

        ViewHolder finalHolder = holder;
        holder.layout.setOnClickListener(view1 -> {
            boolean flag = finalHolder.cb.isChecked();
            finalHolder.cb.setChecked(!flag);
            selectedList.add(tag);
        });
        holder.cb.setOnClickListener(view1 -> {
            if(selectedList.contains(tag)){
                selectedList.remove(tag);
            } else
                selectedList.add(tag);
        });

        return view;
    }

    public Set<Tag> getSelectedTags(){
        return selectedList;
    }

    class ViewHolder{
        RelativeLayout layout;
        TextView text;
        CheckBox cb;

    }
}
