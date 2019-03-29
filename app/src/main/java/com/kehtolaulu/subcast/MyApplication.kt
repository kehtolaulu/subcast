package com.kehtolaulu.subcast

import android.app.Application
import com.kehtolaulu.subcast.di.components.AppComponent
import com.kehtolaulu.subcast.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
    }
}