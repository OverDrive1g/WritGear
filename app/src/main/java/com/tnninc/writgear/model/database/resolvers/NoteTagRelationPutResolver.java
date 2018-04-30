package com.tnninc.writgear.model.database.resolvers;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio3.sqlite.queries.InsertQuery;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;

public class NoteTagRelationPutResolver extends PutResolver<ContentValues> {
    @NonNull
    @Override
    public PutResult performPut(@NonNull StorIOSQLite storIOSQLite, @NonNull ContentValues object) {
        final StorIOSQLite.LowLevel lowLevel = storIOSQLite.lowLevel();
        lowLevel.beginTransaction();
        try {
            final InsertQuery insertQuery = InsertQuery.builder()
                    .table(NoteTagRelationTable.TABLE)
                    .build();

            final long insertedId = lowLevel.insert(insertQuery, object);
            final PutResult putResult = PutResult.newInsertResult(insertedId, insertQuery.table());

            lowLevel.setTransactionSuccessful();
            return putResult;
        } finally {
            lowLevel.endTransaction();
        }
    }
}
