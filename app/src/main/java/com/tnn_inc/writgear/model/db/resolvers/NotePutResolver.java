package com.tnn_inc.writgear.model.db.resolvers;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio3.sqlite.operations.put.PutResult;
import com.tnn_inc.writgear.model.db.entities.Note;

public class NotePutResolver extends PutResolver<Note> {

    @NonNull
    @Override
    public PutResult performPut(@NonNull StorIOSQLite storIOSQLite, @NonNull Note object) {
        return null;
    }
}
