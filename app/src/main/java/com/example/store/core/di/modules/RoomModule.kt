package com.example.store.core.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.store.core.db.StoreDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {
    private val databaseName = "store.db"

    @Provides
    @Singleton
    fun provideDB(app: Application) =
        Room.databaseBuilder(
            app,
            StoreDatabase::class.java,
            databaseName
        ).build()
}