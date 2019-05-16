package com.kehtolaulu.subcast.di.components

import com.kehtolaulu.subcast.di.modules.DetailsModule
import com.kehtolaulu.subcast.di.scope.DetailsScope
import com.kehtolaulu.subcast.presentation.feature.details.presenter.DetailsPresenter
import com.kehtolaulu.subcast.presentation.feature.details.activity.DetailsFragment
import dagger.Component

@DetailsScope
@Component(dependencies = [AppComponent::class], modules = [DetailsModule::class])
interface DetailsComponent {

    fun inject(fragment: DetailsFragment)

    fun providePresenter(): DetailsPresenter
}
