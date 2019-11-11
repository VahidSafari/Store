package com.example.store.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.store.MainActivity
import com.example.store.features.dashboard.data.RemoteDataSource
import com.example.store.features.dashboard.data.StoreRepository
import com.example.store.features.dashboard.ui.DashBoardFragment
import com.example.store.features.dashboard.ui.StoreViewModel
import com.example.store.features.dashboard.ui.StoreViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeRemoteDataSource() : RemoteDataSource

    @ContributesAndroidInjector
    abstract fun contributeStoreRepository() : StoreRepository

    @ContributesAndroidInjector
    abstract fun contributeDashBoardFragment(): DashBoardFragment

}