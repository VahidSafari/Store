package com.example.store.features.dashboard.data

import com.example.store.features.dashboard.ui.ListView
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val listLocalDataSource: ListLocalDataSource
) {

    suspend fun insertListItems(listItems: List<ListView>) =
        listLocalDataSource.insertListItems(listItems.map { it.toListEntity() })

    suspend fun insertParentChildPair(parentChildEntity: ParentChildEntity) =
        listLocalDataSource.insertParentChildPair(parentChildEntity)

    suspend fun getListChildren(parentId: Int) =
        listLocalDataSource.getListChildren(parentId)?.map { it.toListView() }

}