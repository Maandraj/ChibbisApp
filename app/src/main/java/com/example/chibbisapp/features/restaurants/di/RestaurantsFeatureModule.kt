package com.example.chibbisapp.features.restaurants.di


import com.example.chibbisapp.di.NetworkModule
import com.example.chibbisapp.features.restaurants.data.api.RestaurantsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class RestaurantsFeatureModule() {
    @Provides
    fun provideRestaurantsApi(retrofit: Retrofit): RestaurantsApi =
        retrofit.create(RestaurantsApi::class.java)

}