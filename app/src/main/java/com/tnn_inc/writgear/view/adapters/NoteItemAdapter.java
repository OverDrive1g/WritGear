package com.tnn_inc.writgear.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.dto.Note;

import java.util.List;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {

    private List<Note> list;

    public NoteItemAdapter(List<Note> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = list.get(position);
        holder.text.setText(note.getTitle());
        holder.time.setText("10 сек");
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NoteItemAdapter", "onClick: "+ note.getTitle());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.note_title);
            time = itemView.findViewById(R.id.create_time);
        }
    }
}
