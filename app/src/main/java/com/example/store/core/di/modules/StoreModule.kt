package com.example.store.core.di.modules

import android.app.Application
import com.example.store.MainActivity
import com.example.store.features.dashboard.data.StoreLocalDataSource
import com.example.store.features.dashboard.data.StoreRemoteDataSource
import com.example.store.features.dashboard.data.StoreRepository
import com.example.store.features.dashboard.ui.DashBoardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class StoreModule {

    @ContributesAndroidInjector
    abstract fun contributeActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeStoreRemoteDataSource() : StoreRemoteDataSource

    @ContributesAndroidInjector
    abstract fun contributeStoreRepository() : StoreRepository

    @ContributesAndroidInjector
    abstract fun contributeStoreLocalDataSource(): StoreLocalDataSource

    @ContributesAndroidInjector
    abstract fun contributeDashBoardFragment(): DashBoardFragment

}