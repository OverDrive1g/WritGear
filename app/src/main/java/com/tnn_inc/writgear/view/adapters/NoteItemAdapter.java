package com.tnn_inc.writgear.view.adapters;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tnn_inc.writgear.R;
import com.tnn_inc.writgear.presenter.NoteListPresenter;
import com.tnn_inc.writgear.presenter.vo.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {

    private List<Note> list;
    private TypedArray colors;
    private NoteListPresenter presenter;

    public NoteItemAdapter(List<Note> list, NoteListPresenter presenter) {
        this.list = list;
        this.presenter = presenter;
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

        holder.layout.setOnClickListener(view -> presenter.clickNote(note));

        holder.icon.setColorFilter(getRandomColor());
        String iconText = note.getTitle().length() != 0 ? note.getTitle() : note.getText().length() != 0 ? note.getText() : "";
        holder.iconText.setText(iconText.toUpperCase());

        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - Long.parseLong(note.getTime());
        int second = (int) (deltaTime / 1000);
        int minutes = (int) (deltaTime / 60000);
        int hours = (int) (deltaTime / 3600000);
        int day = (int) (deltaTime / 86400000);
        int month = (int) (deltaTime / 2592000000L);
        String time = "";
        if (second >= 0 && minutes == 0 && hours == 0 && day == 0 && month == 0) {
            time = second + " секунд назад";
        } else if (minutes >= 0 && hours == 0 && day == 0 && month == 0) {
            time = minutes + " минут назад";
        } else if (hours >= 0 && day == 0 && month == 0) {
            time = hours + " часов назад";
        } else if (day >= 0 && month == 0) {
            time = day + " дней назад";
        } else if (month >= 0) {
            time = month + " месяев назад";
        }
        holder.time.setText(time);
    }

    public void deleteItem(int id) {
        list.remove(id);
    }

    private int getRandomColor() {
        int returnColor = Color.GRAY;
        int index = (int) (Math.random() * colors.length());
        returnColor = colors.getColor(index, Color.GRAY);
        return returnColor;
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
