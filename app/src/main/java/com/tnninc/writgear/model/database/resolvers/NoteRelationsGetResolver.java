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
import com.tnninc.writgear.model.database.tables.TagsTable;

import java.util.List;


public class NoteRelationsGetResolver extends NoteDTOStorIOSQLiteGetResolver {
    @NonNull
    private final TagDTOStorIOSQLiteGetResolver tagDTOStorIOSQLiteGetResolver;

    public NoteRelationsGetResolver(@NonNull TagDTOStorIOSQLiteGetResolver tagDTOStorIOSQLiteGetResolver) {
        this.tagDTOStorIOSQLiteGetResolver = tagDTOStorIOSQLiteGetResolver;
    }

    @NonNull
    @Override
    public NoteDTO mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
        final NoteDTO note = super.mapFromCursor(storIOSQLite, cursor);

        final List<TagDTO> tags = storIOSQLite
                .get()
                .listOfObjects(TagDTO.class)
                .withQuery(RawQuery.builder()
                    .query("SELECT "
                           + TagsTable.TABLE + ".*"
                           + " FROM " + TagsTable.TABLE
                           + " JOIN " + NoteTagRelationTable.TABLE
                           + " ON " + TagsTable.COLUMN_ID + " = " + NoteTagRelationTable.COLUMN_TAG_ID
                           + " AND " + NoteTagRelationTable.COLUMN_NOTE_ID + " = ?")
                    .args(note.getId())
                    .build())
                .withGetResolver(tagDTOStorIOSQLiteGetResolver)
                .prepare()
                .executeAsBlocking();

        return new NoteDTO(note.getId(), note.getTitle(), note.getText(), note.getCreateDate(),
                note.getGroupId(), note.getColor(), tags);
    }
}
