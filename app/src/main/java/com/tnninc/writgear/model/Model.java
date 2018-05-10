package com.tnninc.writgear.model;

import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.TagDTO;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface Model {
    Flowable<List<NoteDTO>> getNoteList();
    Completable deleteNoteById(int id);
    Completable putNote(NoteDTO note);
    NoteDTO getNoteById(Integer id);

    Completable putTag(TagDTO tag);
    Flowable<List<TagDTO>> getTagList();
    Completable addTagsToNote(List<TagDTO> tags, NoteDTO note);
}
