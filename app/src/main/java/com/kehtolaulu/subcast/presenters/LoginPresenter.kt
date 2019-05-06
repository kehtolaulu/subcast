package com.kehtolaulu.subcast.presenters

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.services.AuthService
import com.kehtolaulu.subcast.services.TokenService
import com.kehtolaulu.subcast.views.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class LoginPresenter(private val tokenService: TokenService, private val authService: AuthService) :
    MvpPresenter<LoginView>() {

    @SuppressLint("CheckResult")
    fun login(username: String, password: String) {
        authService.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                if (it.status == "ERROR") {
                    viewState.showError(it.errorMessage.toString())
                } else {
                    viewState.setSyncFragment()
                    tokenService.saveToken(it.token.toString())
                }
            }
    }

    @SuppressLint("CheckResult")
    fun register(username: String, password: String) {
        authService.register(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                if (it.status == "ERROR") {
                    viewState.showError(it.errorMessage.toString())
                } else {
                    viewState.showSuccess()
                }
            }
    }
}
