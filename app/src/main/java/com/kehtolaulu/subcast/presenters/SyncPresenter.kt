package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.TokenService
import com.kehtolaulu.subcast.views.SyncView

@InjectViewState
class SyncPresenter(private val tokenService: TokenService) : MvpPresenter<SyncView>() {
    fun signOut() {
        tokenService.deleteToken()
        viewState.setLoginFragment()
    }
}