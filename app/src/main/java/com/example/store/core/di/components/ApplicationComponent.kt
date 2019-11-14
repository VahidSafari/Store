package com.example.store.core.di.components

import com.example.store.App
import com.example.store.core.di.modules.ActivityModule
import com.example.store.core.di.modules.RetrofitModule
import com.example.store.core.di.modules.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
    RetrofitModule::class,
    ActivityModule::class,
        RoomModule::class,
    AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(app: App)
}