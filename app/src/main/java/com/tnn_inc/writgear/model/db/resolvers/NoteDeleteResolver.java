package com.tnn_inc.writgear.model.db.resolvers;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio3.sqlite.operations.delete.DeleteResult;
import com.tnn_inc.writgear.model.db.entities.Note;

public class NoteDeleteResolver extends DeleteResolver<Note> {
    @NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull Note object) {
        return null;
    }
}
