package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.SearchModule
import com.kehtolaulu.subcast.di.modules.SyncModule
import com.kehtolaulu.subcast.di.scope.SearchScope
import com.kehtolaulu.subcast.di.scope.SyncScope
import com.kehtolaulu.subcast.presenters.SyncPresenter
import com.kehtolaulu.subcast.ui.SyncFragment
import dagger.Component

@SyncScope
@Component(dependencies = [AppComponent::class], modules = [SyncModule::class])
interface SyncComponent {
    fun inject(fragment: SyncFragment)

    fun providePresenter(): SyncPresenter
}
