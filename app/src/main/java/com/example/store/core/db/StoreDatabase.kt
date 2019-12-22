package com.example.store.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.store.features.dashboard.data.*

@Database(
    entities =
    [
        TopSliderEntity::class,
        CategoryEntity::class,
        PieceEntity::class,
        PieceFts::class,
        CartEntity::class,
        ListEntity::class,
        ParentChildEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun getStoreDao(): StoreDao
}