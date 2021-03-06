package com.tnninc.writgear.presenter.mappers;

import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.presenter.vo.Note;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


public class NoteListMapper implements Function<List<NoteDTO>, List<Note>>{

    @Inject
    public NoteListMapper() {
    }

    @Override
    public List<Note> apply(List<NoteDTO> noteDTOs) {
        if (noteDTOs == null) {
            return null;
        }

        List<Note> notes = Flowable.fromIterable(noteDTOs)
                .map(new Function<NoteDTO, Note>() {
                    @Override
                    public Note apply(NoteDTO noteDTO) {
                        return new Note(noteDTO);
                    }
                })
                .toList()
                .toFlowable()
                .blockingFirst();
        return notes;
    }
}
