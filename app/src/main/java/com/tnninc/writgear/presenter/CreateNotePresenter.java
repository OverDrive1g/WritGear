package com.tnninc.writgear.presenter;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;

import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.fragment.CreateNoteView;

import io.reactivex.disposables.Disposable;

import static com.tnninc.writgear.utils.Converter.getTagDTOsFromTags;

public class CreateNotePresenter extends BasePresenter {

    private CreateNoteView view;
    private TypedArray colors;
    private ActivityCallback activityCallback;

    public CreateNotePresenter(CreateNoteView view, ActivityCallback activityCallback) {
        int arrayId = activityCallback.getActivity().getResources().getIdentifier("mdcolor_400", "array",
                activityCallback.getActivity().getPackageName());

        if (arrayId != 0) {
            colors = activityCallback.getActivity().getResources().obtainTypedArray(arrayId);
        }

        this.view = view;
    }

    public void createNote(Note note) {
        if(note.getText().equals("") && note.getTitle().equals(""))
            return;

        if(note.getId() == null){
            note.setColor(getRandomColor());
            note.setTime(String.valueOf(System.currentTimeMillis()));
        }

        Disposable disposable =
                model.putNote(new NoteDTO(note.getId(), view.getTitle(), view.getText(),
                        note.getTime(), null,
                        note.getColor(), getTagDTOsFromTags(note.getTags())))
                        .subscribe(() -> Log.d("CreateNotePresenter", "push note"),
                                throwable ->  Log.d("CreateNotePresenter", throwable.getMessage()));
        this.addDisposable(disposable);
    }

    private int getRandomColor() {
        int returnColor = Color.GRAY;
        int index = (int) (Math.random() * colors.length());
        returnColor = colors.getColor(index, Color.GRAY);
        return returnColor;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
