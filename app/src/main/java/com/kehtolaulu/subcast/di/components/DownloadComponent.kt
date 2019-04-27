package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.DownloadModule
import com.kehtolaulu.subcast.di.scope.DownloadScope
import com.kehtolaulu.subcast.presenters.DownloadsPresenter
import com.kehtolaulu.subcast.ui.DownloadsFragment
import dagger.Component

@DownloadScope
@Component(dependencies = [AppComponent::class], modules = [DownloadModule::class])
interface DownloadComponent {

    fun inject(fragment: DownloadsFragment)

    fun providePresenter(): DownloadsPresenter
}