package com.tnninc.writgear.di.modules;

import android.content.Context;

import com.pushtorefresh.storio3.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;
import com.tnninc.writgear.model.database.DbOpenHelper;
import com.tnninc.writgear.model.database.entities.NoteDTO;
import com.tnninc.writgear.model.database.entities.NoteDTOSQLiteTypeMapping;
import com.tnninc.writgear.model.database.entities.NoteDTOStorIOSQLiteGetResolver;
import com.tnninc.writgear.model.database.entities.NoteDTOStorIOSQLitePutResolver;
import com.tnninc.writgear.model.database.entities.TagDTO;
import com.tnninc.writgear.model.database.entities.TagDTOStorIOSQLiteGetResolver;
import com.tnninc.writgear.model.database.entities.TagDTOStorIOSQLitePutResolver;
import com.tnninc.writgear.model.database.resolvers.NoteRelationsDeleteResolver;
import com.tnninc.writgear.model.database.resolvers.NoteRelationsGetResolver;
import com.tnninc.writgear.model.database.resolvers.NoteRelationsPutResolver;
import com.tnninc.writgear.model.database.resolvers.NoteTagRelationPutResolver;
import com.tnninc.writgear.model.database.resolvers.TagRelationsDeleteResolver;
import com.tnninc.writgear.model.database.resolvers.TagRelationsGetResolver;
import com.tnninc.writgear.model.database.resolvers.TagRelationsPutResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    private StorIOSQLite storIOSQLite;

    public ModelModule(Context context) {
        final TagDTOStorIOSQLitePutResolver tagDTOStorIOSQLitePutResolver = new TagDTOStorIOSQLitePutResolver();
        final TagDTOStorIOSQLiteGetResolver tagDTOStorIOSQLiteGetResolver = new TagDTOStorIOSQLiteGetResolver();

        final NoteDTOStorIOSQLitePutResolver noteDTOStorIOSQLitePutResolver = new NoteDTOStorIOSQLitePutResolver();
        final NoteDTOStorIOSQLiteGetResolver noteDTOStorIOSQLiteGetResolver = new NoteDTOStorIOSQLiteGetResolver();

        final NoteTagRelationPutResolver noteTagRelationPutResolver = new NoteTagRelationPutResolver();

        this.storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new DbOpenHelper(context))

                .addTypeMapping(NoteDTO.class, SQLiteTypeMapping.<NoteDTO>builder()
                    .putResolver(new NoteRelationsPutResolver(tagDTOStorIOSQLitePutResolver, noteTagRelationPutResolver))
                    .getResolver(new NoteRelationsGetResolver(tagDTOStorIOSQLiteGetResolver))
                    .deleteResolver(new NoteRelationsDeleteResolver())
                    .build())

                .addTypeMapping(TagDTO.class, SQLiteTypeMapping.<TagDTO>builder()
                    .putResolver(new TagRelationsPutResolver(noteDTOStorIOSQLitePutResolver, noteTagRelationPutResolver))
                    .getResolver(new TagRelationsGetResolver(noteDTOStorIOSQLiteGetResolver))
                    .deleteResolver(new TagRelationsDeleteResolver())
                    .build())
                .build();
    }

    @Singleton
    @Provides
    StorIOSQLite provideStorIOSQLite(){
        return storIOSQLite;
    }
}
