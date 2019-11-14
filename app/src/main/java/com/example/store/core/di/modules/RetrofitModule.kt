package com.example.store.core.di.modules

import com.example.store.features.dashboard.data.StoreService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
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
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("http://my-dms.ir/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideStoreService(retrofit: Retrofit) =
        retrofit.create(StoreService::class.java)

}