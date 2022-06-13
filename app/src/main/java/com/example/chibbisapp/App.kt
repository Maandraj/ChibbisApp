package com.example.chibbisapp

import android.app.Application
import com.example.chibbisapp.di.AppComponent
import com.example.chibbisapp.di.DaggerAppComponent

class App: Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
}