package com.example.store.core.di.modules

import com.example.store.features.dashboard.ui.MainActivity
import com.example.store.features.dashboard.data.StoreLocalDataSource
import com.example.store.features.dashboard.data.StoreRemoteDataSource
import com.example.store.features.dashboard.data.StoreRepository
import com.example.store.features.dashboard.ui.CartActivity
import com.example.store.features.dashboard.ui.DashBoardFragment
import com.example.store.features.dashboard.ui.SearchFragment
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
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeDashBoardFragment(): DashBoardFragment

    @ContributesAndroidInjector
    abstract fun contributeCartActivity(): CartActivity
}