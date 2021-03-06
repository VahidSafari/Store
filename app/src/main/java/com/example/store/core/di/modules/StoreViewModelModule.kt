package com.example.store.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.store.core.di.ViewModelKey
import com.example.store.features.dashboard.ui.CartViewModel
import com.example.store.features.dashboard.ui.StoreViewModel
import com.example.store.features.dashboard.ui.StoreViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
abstract class StoreViewModelModule {

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(StoreViewModel::class)
    abstract fun contributeViewModel(storeViewModel: StoreViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: StoreViewModelFactory): ViewModelProvider.Factory
}