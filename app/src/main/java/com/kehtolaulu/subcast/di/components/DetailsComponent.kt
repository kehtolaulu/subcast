package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.DetailsModule
import com.kehtolaulu.subcast.di.scope.DetailsScope
import com.kehtolaulu.subcast.di.scope.DownloadScope
import com.kehtolaulu.subcast.presenters.DetailsPresenter
import com.kehtolaulu.subcast.ui.DetailsFragment
import dagger.Component

@DetailsScope
@Component(dependencies = [AppComponent::class], modules = [DetailsModule::class])
interface DetailsComponent {

    fun inject(fragment: DetailsFragment)

    fun providePresenter(): DetailsPresenter
}
