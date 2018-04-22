package com.tnninc.writgear.presenter;

import android.util.Log;

import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.presenter.vo.Note;
import com.tnninc.writgear.view.fragment.CreateNoteView;

import java.util.Objects;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class CreateNotePresenter extends BasePresenter {
    private CreateNoteView view;

    public CreateNotePresenter(CreateNoteView view) {
        this.view = view;
    }

    public void createNote() {
        Note note = view.getNote();
        Disposable disposable;
        if (note != null) {
            disposable =
                    model.putNote(new NoteDTO(note.getId(), view.getTitle(), view.getText(),
                            String.valueOf(System.currentTimeMillis()), null))
                            .subscribe(() -> {
                                        Log.d("CreateNotePresenter", "push note");
                                    },
                                    throwable -> view.showError(throwable.getMessage()));
        }
        else{
            if (!(!Objects.equals(view.getText(), "") || !Objects.equals(view.getTitle(), "")))
                return;

            NoteDTO newNote = new NoteDTO(null, view.getTitle(), view.getText(),
                    String.valueOf(System.currentTimeMillis()), null);


            model.putNote(newNote)
                    .subscribe(
                            () -> Log.d("CreateNotePresenter", "run: "),
                            throwable -> Log.d("CreateNotePresenter", "accept: "+throwable.getMessage()));
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}
