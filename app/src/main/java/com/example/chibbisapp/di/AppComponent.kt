package com.example.chibbisapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component
@AppScope
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope