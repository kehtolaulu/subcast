package com.kehtolaulu.subcast.presentation.feature.login.presenter

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.data.interactor.AuthInteractor
import com.kehtolaulu.subcast.data.interactor.TokenInteractor
import com.kehtolaulu.subcast.helpers.ERROR
import com.kehtolaulu.subcast.presentation.feature.login.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class LoginPresenter(
    private val tokenInteractor: TokenInteractor,
    private val authInteractor: AuthInteractor,
    private val context: Context
) :
    MvpPresenter<LoginView>() {
    private val compositeDisposable = CompositeDisposable()

    fun login(username: String, password: String) {
        val disposable = authInteractor.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == "ERROR") {
                    viewState.showError(it.errorMessage.toString())
                } else {
                    viewState.setSyncFragment()
                    tokenInteractor.saveToken(it.token.toString())
                }
            }, { viewState.showError("no internet") }
            )
        compositeDisposable.add(disposable)
    }

    fun register(username: String, password: String) {
        if (username.length < 6) {
            viewState.showError(context.getString(R.string.username_error))
        } else if (password.length < 4) {
            viewState.showError(context.getString(R.string.password_error))
        }
        val disposable = authInteractor.register(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                if (it.status == ERROR) {
                    viewState.showError(it.errorMessage.toString())
                } else {
                    viewState.showSuccess()
                }
            }, { viewState.showError(context.getString(R.string.no_internet)) })
        compositeDisposable.add(disposable)
    }

    override fun destroyView(view: LoginView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }
}
