package com.kehtolaulu.subcast.presentation.feature.sync.view

import com.arellomobile.mvp.MvpView

interface SyncView : MvpView {
    fun setLoginFragment()
    fun showSuccess()
    fun showError(message: String)
}
