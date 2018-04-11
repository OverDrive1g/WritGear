package com.tnn_inc.writgear.presenter;

import com.tnn_inc.writgear.model.database.entities.Note;
import com.tnn_inc.writgear.view.fragment.CreateNoteView;

import io.reactivex.disposables.Disposable;

public class CreateNotePresenter extends BasePresenter {
    private CreateNoteView view;
    Disposable disposable;

    public CreateNotePresenter(CreateNoteView view) {
        this.view = view;
    }

    public void createNote(){
        com.tnn_inc.writgear.presenter.mappers.vo.Note note = view.getNote();
        if(note != null){
            disposable =
                    model.putNote(new Note(note.getId(), note.getTitle(), note.getText(),
                            note.getCreateDate(), note.getGroupId()))
                            .subscribe(() -> {},
                             throwable -> view.showError(throwable.getMessage()));
        }
        disposable =
                model.putNote(new Note(null, view.getTitle(), view.getText(),
                String.valueOf(System.currentTimeMillis()), null))
                .subscribe(() -> {},
                            throwable -> view.showError(throwable.getMessage()));
    }

    private void dispose(){
        if( disposable != null){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }

    @Override
    public void onStop() {
        dispose();
        super.onStop();
    }
}
