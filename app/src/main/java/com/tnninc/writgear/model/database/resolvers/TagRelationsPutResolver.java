package com.tnninc.writgear.model.database.resolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.NoteDTOStorIOSQLitePutResolver;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.model.database.entities.TagDTOStorIOSQLitePutResolver;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;

import java.util.ArrayList;
import java.util.List;


public class TagRelationsPutResolver extends TagDTOStorIOSQLitePutResolver {

    @NonNull
    private final NoteDTOStorIOSQLitePutResolver noteDTOStorIOSQLitePutResolver;

    @NonNull
    private final NoteTagRelationPutResolver noteTagRelationPutResolver;

    public TagRelationsPutResolver(
            @NonNull NoteDTOStorIOSQLitePutResolver noteDTOStorIOSQLitePutResolver,
            @NonNull NoteTagRelationPutResolver noteTagRelationPutResolver) {
        this.noteDTOStorIOSQLitePutResolver = noteDTOStorIOSQLitePutResolver;
        this.noteTagRelationPutResolver = noteTagRelationPutResolver;
    }

    @NonNull
    @Override
    public PutResult performPut(@NonNull StorIOSQLite storIOSQLite, @NonNull TagDTO object) {
        final StorIOSQLite.LowLevel lowLevel = storIOSQLite.lowLevel();
        lowLevel.beginTransaction();
        try {
            final PutResult putResult = super.performPut(storIOSQLite, object);

            final long tagId = putResult.wasInserted() ? putResult.insertedId() : object.getId();

            storIOSQLite.delete()
                    .byQuery(DeleteQuery.builder().table(NoteTagRelationTable.TABLE)
                             .where(NoteTagRelationTable.COLUMN_TAG_ID + " = ?")
                             .whereArgs(tagId)
                             .build())
                    .prepare()
                    .executeAsBlocking();

            final List<NoteDTO> notes = object.getNotes();
            if(notes != null){
                storIOSQLite.put()
                        .objects(notes)
                        .withPutResolver(noteDTOStorIOSQLitePutResolver)
                        .prepare()
                        .executeAsBlocking();

                final List<ContentValues> contentValuesList = new ArrayList<>(notes.size());

                for (NoteDTO note :notes) {
                    final ContentValues cv = new ContentValues(2);
                    cv.put(NoteTagRelationTable.COLUMN_TAG_ID, tagId);
                    cv.put(NoteTagRelationTable.COLUMN_NOTE_ID, note.getId());
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
