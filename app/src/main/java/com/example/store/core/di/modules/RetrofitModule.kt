package com.example.store.core.di.modules

import com.example.store.App
import com.example.store.core.api.NetworkHandler
import com.example.store.features.dashboard.data.StoreService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .connectTimeout(20,TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request: Request = chain
                    .request()
                    .newBuilder()
                    .build()
                chain.proceed(request)
            }
            .build()
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://my-dms.ir/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideStoreService(retrofit: Retrofit): StoreService =
        retrofit.create(StoreService::class.java)

    @Provides
    @Singleton
    fun provideNetworkHandler(app: App) = NetworkHandler(app)

}