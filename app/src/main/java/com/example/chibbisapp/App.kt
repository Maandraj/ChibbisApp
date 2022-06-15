package com.example.chibbisapp

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.chibbisapp.di.AppComponent
import com.example.chibbisapp.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
val Context.appComponent: AppComponent
    get() =
        when (this) {
            is App -> appComponent
            else -> this.applicationContext.appComponent
        }