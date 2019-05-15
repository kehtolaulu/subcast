package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView

interface LoginView : MvpView {
    fun setSyncFragment()
    fun showError(text: String)
    fun showSuccess()
}