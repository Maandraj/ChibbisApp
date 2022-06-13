package com.example.chibbisapp.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideOkHttps() : OkHttpClient  = OkHttpClient.Builder().build()

    @Provides
    @AppScope
    fun provideMoshi() : Moshi  = Moshi.Builder().build()

    @Provides
    @AppScope
    fun provideRetrofit(moshi:Moshi, okHttpClient: OkHttpClient) : Retrofit  = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

}
private const val BASE_URL = "https://front-task.chibbistest.ru/"
