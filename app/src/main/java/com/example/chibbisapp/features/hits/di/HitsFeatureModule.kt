package com.example.chibbisapp.features.hits.di

import android.content.Context
import com.example.chibbisapp.di.NetworkModule
import com.example.chibbisapp.features.hits.data.api.HitsApi
import com.example.chibbisapp.features.hits.presentation.HitsFragment
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class HitsFeatureModule {




    @Provides
    fun provideHitsApi(retrofit: Retrofit) = retrofit.create(HitsApi::class.java)
}