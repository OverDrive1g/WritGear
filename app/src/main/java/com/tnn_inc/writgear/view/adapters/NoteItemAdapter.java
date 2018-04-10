package com.tnn_inc.writgear.view.adapters;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.model.database.entities.Note;

import java.util.List;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {

    private List<Note> list;
    private TypedArray colors;

    public NoteItemAdapter(List<Note> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_list_item, viewGroup, false);

        int arrayId = viewGroup.getResources().getIdentifier("mdcolor_400", "array",
                viewGroup.getContext().getPackageName());

        if (arrayId != 0) {
            colors = viewGroup.getResources().obtainTypedArray(arrayId);
        }

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = list.get(position);
        holder.text.setText(note.getTitle());
        holder.time.setText(" ");
        holder.text.setOnClickListener(view -> {
            // TODO: 08.04.2018 открытие в новом view
            Log.d("NoteItemAdapter", "onClick: "+ note.getTitle());
        });
        holder.icon.setColorFilter(getRandomColor());
        String iconText = note.getTitle().length() != 0? note.getTitle(): note.getText().length() != 0?note.getText():"";
        holder.iconText.setText(iconText.toUpperCase());
    }

    private int getRandomColor(){
        int returnColor = Color.GRAY;
        int index = (int) (Math.random() * colors.length());
        returnColor = colors.getColor(index, Color.GRAY);
        return returnColor;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Note getNoteByPosition(int position){
        return list.get(position);
    }

    public void refreshAfterDelete(int position){
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView iconText;
        TextView time;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.note_title);
            time = itemView.findViewById(R.id.create_time);
            icon = itemView.findViewById(R.id.icon);
            iconText = itemView.findViewById(R.id.icon_text);
        }
    }
}
