package com.kehtolaulu.subcast

import android.app.Application
import com.kehtolaulu.subcast.di.components.AppComponent
import com.kehtolaulu.subcast.di.components.DaggerAppComponent
import com.kehtolaulu.subcast.di.modules.AppModule

class MyApplication : Application() {
    companion object {
        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
