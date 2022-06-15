package com.example.chibbisapp.features.hits.di

import com.example.chibbisapp.features.hits.presentation.HitsFragment
import dagger.Component

@Component(modules = [HitsFeatureModule::class])
 interface HitsComponent {

    fun inject(hitsFragment: HitsFragment)

    @Component.Builder
    interface Builder {
        fun build(): HitsComponent
    }
}