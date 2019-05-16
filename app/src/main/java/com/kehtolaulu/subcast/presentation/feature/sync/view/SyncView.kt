package com.kehtolaulu.subcast.views

import com.arellomobile.mvp.MvpView

interface SyncView : MvpView {
    fun setLoginFragment()
    fun showSuccess()
    fun showError(message: String)
}
