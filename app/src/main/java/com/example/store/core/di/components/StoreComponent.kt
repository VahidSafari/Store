package com.example.store.core.di.components

import com.example.store.MainActivity
import com.example.store.core.di.modules.AppModule
import com.example.store.core.di.modules.RetrofitModule
import com.example.store.core.di.modules.StoreModule
import com.example.store.features.dashboard.data.RemoteDataSource
import com.example.store.features.dashboard.data.StoreRepository
import com.example.store.features.dashboard.ui.StoreViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    AppModule::class,
    RetrofitModule::class,
    StoreModule::class
    ]
)
interface StoreComponent {
    fun inject(target: StoreRepository)
    fun inject(target: StoreViewModel)
    fun inject(target: RemoteDataSource)
    fun inject(target: MainActivity)
}