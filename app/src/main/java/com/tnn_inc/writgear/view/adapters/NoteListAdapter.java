package com.tnn_inc.writgear.view.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.dto.Note;

import java.util.List;


public class NoteListAdapter extends BaseAdapter {

    private Context ctx;
    private LayoutInflater layoutInflater;
    private List<Note> objects;

    public NoteListAdapter(Context ctx, List<Note> objects) {
        this.ctx = ctx;
        this.objects = objects;
        this.layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.note_list_item, null);
        }
        Note note = getNote(i);
        ((TextView)view.findViewById(R.id.create_time)).setText(note.getCreateDate().toString());
        ((TextView)view.findViewById(R.id.note_title)).setText(note.getTitle());
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    Note getNote(int position){
        return (Note) getItem(position);
    }
}
