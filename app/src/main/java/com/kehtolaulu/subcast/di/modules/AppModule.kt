package com.kehtolaulu.subcast.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(private var context: Context) {

    @Provides
    fun context(): Context {
        return context.applicationContext
    }
}