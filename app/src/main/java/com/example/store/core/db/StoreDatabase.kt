package com.example.store.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.store.features.dashboard.data.CategoryEntity
import com.example.store.features.dashboard.data.PieceEntity
import com.example.store.features.dashboard.data.StoreDao
import com.example.store.features.dashboard.data.TopSliderEntity

@Database(entities = [TopSliderEntity::class,CategoryEntity::class,PieceEntity::class], version = 1)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun getStoreDao(): StoreDao
}