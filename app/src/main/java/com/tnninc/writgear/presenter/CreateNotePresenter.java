package com.tnninc.writgear.presenter;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;

import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.ActivityCallback;
import com.tnninc.writgear.view.fragment.CreateNoteView;

import java.util.Objects;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

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

    public void createNote() {
        Note note = view.getNote();
        Disposable disposable;
        if (note != null) {
            disposable =
                    model.putNote(new NoteDTO(note.getId(), view.getTitle(), view.getText(),
                            String.valueOf(System.currentTimeMillis()), null, getRandomColor()))
                            .subscribe(() -> Log.d("CreateNotePresenter", "push note"),
                                    throwable ->  Log.d("CreateNotePresenter", throwable.getMessage()));
        }
        else{
            if (!(!Objects.equals(view.getText(), "") || !Objects.equals(view.getTitle(), "")))
                return;

            NoteDTO newNote = new NoteDTO(null, view.getTitle(), view.getText(),
                    String.valueOf(System.currentTimeMillis()), null, getRandomColor());


            model.putNote(newNote)
                    .subscribe(
                            () -> Log.d("CreateNotePresenter", "run: "),
                            throwable -> Log.d("CreateNotePresenter", "accept: "+throwable.getMessage()));
        }
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
