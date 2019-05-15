package com.kehtolaulu.subcast

import android.app.Application
import com.kehtolaulu.subcast.di.components.AppComponent
import com.kehtolaulu.subcast.di.components.DaggerAppComponent
import com.kehtolaulu.subcast.di.modules.ApiModule
import com.kehtolaulu.subcast.di.modules.AppModule
import com.kehtolaulu.subcast.di.modules.NetModule
import com.kehtolaulu.subcast.di.modules.PlayerModule

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .apiModule(ApiModule())
            .build()
    }
}
