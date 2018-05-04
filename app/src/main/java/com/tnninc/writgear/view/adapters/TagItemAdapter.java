package com.tnninc.writgear.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.model.database.entities.TagDTO;

import java.util.List;

public class TagItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<TagDTO> tags;

    public TagItemAdapter(Context context, List<TagDTO> tags) {
        this.context = context;
        this.tags = tags;
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
        return tags.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null){
            view = layoutInflater.inflate(R.layout.tag_list_item, viewGroup, false);

            holder = new ViewHolder();

            holder.text = (TextView) view.findViewById(R.id.tag_text_view);
            holder.cb = (CheckBox) view.findViewById(R.id.tag_check_box);

            view.setTag(holder);
        } else
            holder = (ViewHolder)view.getTag();

        TagDTO tag = tags.get(i);

        holder.text.setText(tag.getName());

        //if in this tag exist current tag then cb.setChecked(true);

        return view;
    }

    class ViewHolder{

        TextView text;
        CheckBox cb;

    }
}
