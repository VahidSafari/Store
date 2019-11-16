package com.example.store.core.di.modules

import androidx.room.Room
import com.example.store.App
import com.example.store.core.db.StoreDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {
    private val databaseName = "store.db"

    @Provides
    @Singleton
    fun provideDB(app: App) =
        Room.databaseBuilder(
            app,
            StoreDatabase::class.java,
            databaseName
        ).build()
}