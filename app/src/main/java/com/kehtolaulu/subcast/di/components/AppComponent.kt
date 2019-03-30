package com.kehtolaulu.subcast.di.components

import android.content.Context
import com.kehtolaulu.subcast.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun provideContext(): Context
}