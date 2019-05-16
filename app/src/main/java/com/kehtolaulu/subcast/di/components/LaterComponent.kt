package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.LaterModule
import com.kehtolaulu.subcast.di.scope.LaterScope
import com.kehtolaulu.subcast.presentation.feature.playlater.presenter.LaterPresenter
import com.kehtolaulu.subcast.presentation.feature.playlater.activity.LaterFragment
import dagger.Component

@LaterScope
@Component(dependencies = [AppComponent::class], modules = [LaterModule::class])
interface LaterComponent {

    fun inject(fragment: LaterFragment)

    fun providePresenter(): LaterPresenter
}