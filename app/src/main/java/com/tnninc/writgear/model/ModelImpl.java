package com.tnninc.writgear.model;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio3.sqlite.queries.Query;
import com.tnninc.writgear.di.App;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.model.database.tables.NotesTable;
import com.tnninc.writgear.model.database.tables.TagsTable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ModelImpl implements Model {

    @Inject
    public StorIOSQLite storIOSQLite;

    public ModelImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Flowable<List<NoteDTO>> getNoteList() {
        return storIOSQLite
                .get()
                .listOfObjects(NoteDTO.class)
                .withQuery(NotesTable.QUERY_ALL)
                .prepare()
                .asRxFlowable(BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteNoteById(int id) {
        return storIOSQLite
                .delete()
                .byQuery(DeleteQuery.builder()
                    .table(NotesTable.TABLE)
                    .where(NotesTable.COLUMN_ID + " = ?")
                    .whereArgs(id)
                    .build())
                .prepare()
                .asRxCompletable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<PutResult> putNote(NoteDTO note) {
        return storIOSQLite
                .put()
                .object(note)
                .prepare()
                .asRxSingle()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable putTag(TagDTO tag) {
        return storIOSQLite
                .put()
                .object(tag)
                .prepare()
                .asRxCompletable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<List<TagDTO>> getTagList() {
        return storIOSQLite
                .get()
                .listOfObjects(TagDTO.class)
                .withQuery(TagsTable.QUERY_ALL)
                .prepare()
                .asRxFlowable(BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable addTagsToNote(List<TagDTO> tags, NoteDTO n) {
        List<TagDTO> oldTags = n.getTags();
        List<TagDTO> newTags = new ArrayList<>();

        if (oldTags != null)
            newTags.addAll(oldTags);

        newTags.addAll(tags);

        return storIOSQLite
                .put()
                .object(new NoteDTO(n.getId(), n.getTitle(), n.getText(), n.getCreateDate(), n.getGroupId(), n.getColor(), newTags))
                .prepare()
                .asRxCompletable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public NoteDTO getNoteById(Integer id) {
        return storIOSQLite
                .get()
                .object(NoteDTO.class)
                .withQuery(Query.builder()
                        .table(NotesTable.TABLE)
                        .where("id = ?")
                        .whereArgs(id)
                        .build())
                .prepare()
                .executeAsBlocking();
    }
}
