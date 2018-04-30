package com.tnninc.writgear.model.database.resolvers;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.delete.DeleteResult;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.NoteDTOStorIOSQLiteDeleteResolver;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;
import com.tnninc.writgear.model.database.tables.NotesTable;


public class NoteRelationsDeleteResolver extends NoteDTOStorIOSQLiteDeleteResolver {
    @NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull NoteDTO object) {
        final StorIOSQLite.LowLevel lowLevel = storIOSQLite.lowLevel();
        lowLevel.beginTransaction();
        try {

            final DeleteResult deleteResult = super.performDelete(storIOSQLite, object);

            storIOSQLite.delete()
                    .byQuery(DeleteQuery.builder()
                        .table(NotesTable.TABLE)
                        .where(NoteTagRelationTable.COLUMN_NOTE_ID + " = ?")
                        .whereArgs(object.getId())
                        .build())
                    .prepare()
                    .executeAsBlocking();

            lowLevel.setTransactionSuccessful();

            return deleteResult;
        } finally {
            lowLevel.endTransaction();
        }
    }
}
