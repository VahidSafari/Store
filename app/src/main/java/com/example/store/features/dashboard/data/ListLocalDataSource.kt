package com.example.store.features.dashboard.data

import com.example.store.core.db.StoreDatabase
import javax.inject.Inject

class ListLocalDataSource @Inject constructor(
    val db: StoreDatabase
) {

    suspend fun insertListItems(listItems: List<ListEntity>) =
        db.getListDao().insertListItems(listItems)

    suspend fun insertParentChildPair(parentChildEntity: ParentChildEntity) =
        db.getListDao().insertParentChildPair(parentChildEntity)

    suspend fun getListChildren(parentId: Int) = db.getListDao().getListChildren(parentId)
}