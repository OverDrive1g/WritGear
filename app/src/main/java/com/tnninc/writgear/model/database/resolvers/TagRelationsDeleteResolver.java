package com.tnninc.writgear.model.database.resolvers;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.delete.DeleteResult;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.model.database.entities.TagDTOStorIOSQLiteDeleteResolver;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;

public class TagRelationsDeleteResolver extends TagDTOStorIOSQLiteDeleteResolver {
    @NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull TagDTO object) {
        final StorIOSQLite.LowLevel lowLevel = storIOSQLite.lowLevel();
        lowLevel.beginTransaction();

        try {
            final DeleteResult deleteResult = super.performDelete(storIOSQLite, object);

            storIOSQLite.delete()
                    .byQuery(DeleteQuery.builder()
                    .table(NoteTagRelationTable.TABLE)
                    .where(NoteTagRelationTable.COLUMN_TAG_ID + " = ?")
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
