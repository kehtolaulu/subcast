package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun showSuccess(message: String)
}