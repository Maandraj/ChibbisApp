package com.example.chibbisapp.di

import android.app.Application
import android.content.Context
import com.example.chibbisapp.MainActivity
import com.example.chibbisapp.features.hits.presentation.HitsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [CoreModule::class])
@AppScope
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope