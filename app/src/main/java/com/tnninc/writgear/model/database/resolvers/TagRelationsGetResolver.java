package com.tnninc.writgear.model.database.resolvers;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.RawQuery;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.NoteDTOStorIOSQLiteGetResolver;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.model.database.entities.TagDTOStorIOSQLiteGetResolver;
import com.tnninc.writgear.model.database.tables.NoteTagRelationTable;
import com.tnninc.writgear.model.database.tables.NotesTable;

import java.util.List;


public class TagRelationsGetResolver extends TagDTOStorIOSQLiteGetResolver {
    @NonNull
    private final NoteDTOStorIOSQLiteGetResolver noteDTOStorIOSQLiteGetResolver;

    public TagRelationsGetResolver(@NonNull NoteDTOStorIOSQLiteGetResolver noteDTOStorIOSQLiteGetResolver) {
        this.noteDTOStorIOSQLiteGetResolver = noteDTOStorIOSQLiteGetResolver;
    }

    @NonNull
    @Override
    public TagDTO mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
        final TagDTO tag = super.mapFromCursor(storIOSQLite, cursor);

        final List<NoteDTO> notes = storIOSQLite
                .get()
                .listOfObjects(NoteDTO.class)
                .withQuery(RawQuery.builder()
                    .query("SELECT "
                            + NotesTable.TABLE + ".*"
                            + " FROM " + NotesTable.TABLE
                            + " JOIN " + NoteTagRelationTable.TABLE
                            + " ON " + NotesTable.COLUMN_ID + " = " + NoteTagRelationTable.COLUMN_NOTE_ID
                            + " AND " + NoteTagRelationTable.COLUMN_TAG_ID + " = ?"
                    )
                    .args(tag.getId())
                    .build())
                .withGetResolver(noteDTOStorIOSQLiteGetResolver)
                .prepare()
                .executeAsBlocking();

        return new TagDTO(tag.getId(), tag.getName(), notes);
    }
}
