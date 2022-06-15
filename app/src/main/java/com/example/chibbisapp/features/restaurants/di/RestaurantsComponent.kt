package com.example.chibbisapp.features.restaurants.di

import com.example.chibbisapp.features.restaurants.presentation.RestaurantsFragment
import dagger.Component

@Component(modules = [RestaurantsFeatureModule::class])
interface RestaurantsComponent {
    fun inject(restaurantsFragment: RestaurantsFragment)
    @Component.Builder
    interface Builder {

        fun build(): RestaurantsComponent
    }
}

