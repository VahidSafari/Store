package com.example.store.features.dashboard.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class ListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertListItems(listItems: List<ListEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertParentChildPair(parentChildEntity: ParentChildEntity)

    @Query("""
        SELECT id, name, has_child
        FROM list_entity as le INNER JOIN parent_child_entity as pe ON le.id=pe.child_id
        WHERE pe.parent_id=:parentId""")
    abstract suspend fun getListChildren(parentId: Int): List<ListEntity>?

}