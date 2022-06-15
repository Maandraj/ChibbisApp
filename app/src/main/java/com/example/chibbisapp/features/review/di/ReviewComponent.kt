package com.example.chibbisapp.features.review.di

import com.example.chibbisapp.features.review.presentation.ReviewFragment
import dagger.Component

@Component(modules = [ReviewFeatureModule::class])
interface ReviewComponent {
    fun inject(reviewFragment: ReviewFragment)

    @Component.Builder
    interface Builder {
        fun build(): ReviewComponent
    }
}