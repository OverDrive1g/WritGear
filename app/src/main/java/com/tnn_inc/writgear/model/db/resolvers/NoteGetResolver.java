package com.tnn_inc.writgear.model.db.resolvers;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.get.GetResolver;
import com.pushtorefresh.storio3.sqlite.queries.Query;
import com.pushtorefresh.storio3.sqlite.queries.RawQuery;
import com.tnn_inc.writgear.model.db.entities.Note;

public class NoteGetResolver extends GetResolver<Note> {
    @NonNull
    @Override
    public Note mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
        return null;
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull StorIOSQLite storIOSQLite, @NonNull RawQuery rawQuery) {
        return null;
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull StorIOSQLite storIOSQLite, @NonNull Query query) {
        return null;
    }
}
