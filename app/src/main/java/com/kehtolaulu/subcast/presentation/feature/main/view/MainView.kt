package com.kehtolaulu.subcast.presentation.feature.main.view

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun showSuccess(message: String)
}
