package com.example.chibbisapp.di

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class CoreModule() {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}

@Module
interface ContextModule {
    @Binds
    fun provideContext(application: Application): Context
}

@Module(includes = [CoreModule::class])
class NetworkModule {

    @Provides
    fun provideOkHttps(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(6, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS).build()

    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

}

private const val BASE_URL = "https://front-task.chibbistest.ru/"
