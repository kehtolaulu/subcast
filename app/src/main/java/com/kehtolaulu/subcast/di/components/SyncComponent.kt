package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.SyncModule
import com.kehtolaulu.subcast.di.scope.SyncScope
import com.kehtolaulu.subcast.presentation.feature.sync.presenter.SyncPresenter
import com.kehtolaulu.subcast.presentation.feature.sync.activity.SyncFragment
import dagger.Component

@SyncScope
@Component(dependencies = [AppComponent::class], modules = [SyncModule::class])
interface SyncComponent {
    fun inject(fragment: SyncFragment)

    fun providePresenter(): SyncPresenter
}
