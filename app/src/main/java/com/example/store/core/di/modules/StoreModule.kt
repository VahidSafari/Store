package com.example.store.core.di.modules

import com.example.store.features.dashboard.data.*
import com.example.store.features.dashboard.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [StoreViewModelModule::class])
interface StoreModule {

    @ContributesAndroidInjector
    fun contributeActivity() : MainActivity

    @ContributesAndroidInjector
    fun contributeStoreRemoteDataSource() : StoreRemoteDataSource

    @ContributesAndroidInjector
    fun contributeStoreRepository() : StoreRepository

    @ContributesAndroidInjector
    fun contributeStoreLocalDataSource(): StoreLocalDataSource

    @ContributesAndroidInjector
    fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    fun contributeDashBoardFragment(): DashBoardFragment

    @ContributesAndroidInjector
    fun contributeCartActivity(): CartActivity

    @ContributesAndroidInjector
    fun contributeItemSpecificationActivity(): ItemSpecificationActivity

    @ContributesAndroidInjector
    fun contributeOrderActivity(): OrderActivity

    @ContributesAndroidInjector
    fun contributeListLocalDataSource(): ListLocalDataSource

    @ContributesAndroidInjector
    fun contributeListRepository(): ListRepository

    @ContributesAndroidInjector
    fun contributeListFragment(): ListFragment
}