package com.kehtolaulu.subcast.presentation.feature.login.view

import com.arellomobile.mvp.MvpView

interface LoginView : MvpView {
    fun setSyncFragment()
    fun showError(text: String)
    fun showSuccess()
}
