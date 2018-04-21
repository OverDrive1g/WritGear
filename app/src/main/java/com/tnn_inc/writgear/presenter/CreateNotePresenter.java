package com.tnn_inc.writgear.presenter;

import android.util.Log;

import com.tnn_inc.writgear.model.database.entities.NoteDTO;
import com.tnn_inc.writgear.presenter.vo.Note;
import com.tnn_inc.writgear.view.fragment.CreateNoteView;

import java.util.Objects;

import io.reactivex.disposables.Disposable;

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
            disposable =
                    model.putNote(newNote)
                            .subscribe(
                                    () -> view.showMessage("Зметка создана"),
                                    throwable -> view.showError(throwable.getMessage()));
        }
        addDisposable(disposable);
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}
