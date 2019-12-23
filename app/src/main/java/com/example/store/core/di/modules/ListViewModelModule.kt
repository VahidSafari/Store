package com.example.store.core.di.modules

import androidx.lifecycle.ViewModel
import com.example.store.core.di.ViewModelKey
import com.example.store.features.dashboard.ui.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    fun contributeViewModel(listViewModel: ListViewModel): ViewModel
}