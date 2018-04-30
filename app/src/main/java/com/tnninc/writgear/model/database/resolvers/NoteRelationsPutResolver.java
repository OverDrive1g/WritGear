package com.tnninc.writgear.model.database.resolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.NoteDTOStorIOSQLitePutResolver;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.model.database.entities.TagDTOStorIOSQLitePutResolver;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;

import java.util.ArrayList;
import java.util.List;


public class NoteRelationsPutResolver extends NoteDTOStorIOSQLitePutResolver {
    @NonNull
    private final TagDTOStorIOSQLitePutResolver tagDTOStorIOSQLitePutResolver;

    @NonNull
    private final NoteTagRelationPutResolver noteTagRelationPutResolver;

    public NoteRelationsPutResolver(
            @NonNull TagDTOStorIOSQLitePutResolver tagDTOStorIOSQLitePutResolver,
            @NonNull NoteTagRelationPutResolver noteTagRelationPutResolver) {
        this.tagDTOStorIOSQLitePutResolver = tagDTOStorIOSQLitePutResolver;
        this.noteTagRelationPutResolver = noteTagRelationPutResolver;
    }

    @NonNull
    @Override
    public PutResult performPut(@NonNull StorIOSQLite storIOSQLite, @NonNull NoteDTO object) {
        final StorIOSQLite.LowLevel lowLevel = storIOSQLite.lowLevel();
        lowLevel.beginTransaction();
        try{
            final PutResult putResult = super.performPut(storIOSQLite, object);

            final long noteId = putResult.wasInserted() ? putResult.insertedId() : object.getId();

            storIOSQLite.delete()
                    .byQuery(DeleteQuery.builder().table(NoteTagRelationTable.TABLE)
                            .where(NoteTagRelationTable.COLUMN_NOTE_ID + " = ?")
                            .whereArgs(noteId)
                            .build())
                    .prepare()
                    .executeAsBlocking();

            final List<TagDTO> tags = object.getTags();
            if(tags != null){
                final PutResults<TagDTO> tagsPutResults = storIOSQLite
                        .put()
                        .objects(tags)
                        .withPutResolver(tagDTOStorIOSQLitePutResolver)
                        .prepare()
                        .executeAsBlocking();

                final List<ContentValues> contentValuesList = new ArrayList<>(tags.size());
                for (TagDTO tag :tags) {
                    final PutResult tagPutResult = tagsPutResults.results().get(tag);
                    final long tagId = tagPutResult.wasInserted() ? tagPutResult.insertedId() : tag.getId();
                    final ContentValues cv = new ContentValues(2);
                    cv.put(NoteTagRelationTable.COLUMN_TAG_ID, tagId);
                    cv.put(NoteTagRelationTable.COLUMN_NOTE_ID, noteId);
                    contentValuesList.add(cv);
                }
                storIOSQLite
                        .put()
                        .contentValues(contentValuesList)
                        .withPutResolver(noteTagRelationPutResolver)
                        .prepare()
                        .executeAsBlocking();
            }

            lowLevel.setTransactionSuccessful();
            return putResult;
        } finally {
            lowLevel.endTransaction();
        }
    }
}
