package com.tnninc.writgear.model.database.entities;

import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteCreator;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;
import com.tnninc.writgear.model.database.tables.GroupsTable;

@StorIOSQLiteType(table = GroupsTable.TABLE, generateTableClass = false)
public class GroupDTO {

    @Nullable
    @StorIOSQLiteColumn(name = GroupsTable.COLUMN_ID, key = true)
    private Integer id;

    @Nullable
    @StorIOSQLiteColumn(name = GroupsTable.COLUMN_NAME)
    private String name;

    private GroupDTO(){
    }

    public GroupDTO(@Nullable Integer id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @StorIOSQLiteCreator
    static GroupDTO create(@Nullable Integer id, @Nullable String name){
        return new GroupDTO(id, name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GroupDTO group = (GroupDTO) obj;
        if (id != null ? !id.equals(group.id) : group.id != null) return false;
        return name.equals(group.getName());

    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
