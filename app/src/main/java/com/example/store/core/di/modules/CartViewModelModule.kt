package com.example.store.core.di.modules

import androidx.lifecycle.ViewModel

import com.example.store.core.di.ViewModelKey
import com.example.store.features.dashboard.ui.CartViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CartViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun contributeViewModel(cartViewModel:CartViewModel):ViewModel
}
