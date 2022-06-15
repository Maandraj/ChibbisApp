package com.example.chibbisapp.features.review.di

import com.example.chibbisapp.di.NetworkModule
import com.example.chibbisapp.features.review.data.api.ReviewApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ReviewFeatureModule {

    @Provides
    fun provideReviewApi(retrofit: Retrofit) = retrofit.create(ReviewApi::class.java)
}