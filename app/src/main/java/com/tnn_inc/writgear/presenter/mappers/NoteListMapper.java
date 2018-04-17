package com.tnn_inc.writgear.presenter.mappers;

import com.tnn_inc.writgear.model.database.entities.NoteDTO;
import com.tnn_inc.writgear.presenter.vo.Note;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


public class NoteListMapper implements Function<List<NoteDTO>, List<Note>>{

    @Inject
    public NoteListMapper() {
    }

    @Override
    public List<Note> apply(List<NoteDTO> noteDTOs) throws Exception {
        if (noteDTOs == null) {
            return null;
        }

        List<Note> notes = Flowable.fromIterable(noteDTOs)
                .map(noteDTO -> new Note(noteDTO.getTitle(), noteDTO.getText()))
                .toList()
                .toFlowable()
                .blockingFirst();
        return notes;
    }
}
