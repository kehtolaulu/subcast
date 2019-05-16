package com.kehtolaulu.subcast

import android.app.Application
import com.kehtolaulu.subcast.di.components.AppComponent
import com.kehtolaulu.subcast.di.components.DaggerAppComponent

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
    }
}
