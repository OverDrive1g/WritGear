package com.tnninc.writgear.view.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tnninc.writgear.R;
import com.tnninc.writgear.presenter.NoteListPresenter;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.utils.Converter;
import com.tnninc.writgear.utils.RegExHelper;

import java.util.List;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {

    private List<Note> list;
    private NoteListPresenter presenter;

    public NoteItemAdapter(List<Note> list, NoteListPresenter presenter) {
        this.list = list;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = list.get(position);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clickNote(note);
            }
        });

        holder.icon.setColorFilter(note.getColor());
        String iconText = "";

        if (!note.getTitle().equals("")) {
            holder.text.setText(note.getTitle());
            iconText = note.getTitle();
        } else {
            holder.text.setText(note.getText().split(" ")[0]);
            iconText = note.getText();
        }
        holder.iconText.setText(RegExHelper.getFirstSimbolFromString(iconText.toUpperCase()));

        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - Long.parseLong(note.getTime());

        holder.time.setText(Converter.getTimeAgoByDeltatime(deltaTime));
    }

    public void deleteItem(int id) {
        list.remove(id);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Note getNoteByPosition(int position) {
        return list.get(position);
    }

    public void refreshAfterDelete(int position) {
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView layout;
        ImageView icon;
        TextView iconText;
        TextView time;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_layout);
            text = itemView.findViewById(R.id.note_title);
            time = itemView.findViewById(R.id.create_time);
            icon = itemView.findViewById(R.id.icon);
            iconText = itemView.findViewById(R.id.icon_text);
        }
    }
}
