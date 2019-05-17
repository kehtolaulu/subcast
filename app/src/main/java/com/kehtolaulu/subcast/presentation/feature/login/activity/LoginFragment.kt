package com.kehtolaulu.subcast.presentation.feature.login.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.di.components.DaggerLoginComponent
import com.kehtolaulu.subcast.di.modules.LoginModule
import com.kehtolaulu.subcast.presentation.feature.login.presenter.LoginPresenter
import com.kehtolaulu.subcast.presentation.feature.login.view.LoginView
import com.kehtolaulu.subcast.presentation.feature.main.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

class LoginFragment : MvpAppCompatFragment(), LoginView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = presenter

    override fun showSuccess() {
        Toast.makeText(activity, getString(R.string.success_sign_up), Toast.LENGTH_SHORT).show()
    }

    override fun setSyncFragment() {
        var activity = activity as MainActivity
        activity.setSyncFragment()
    }

    override fun showError(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        DaggerLoginComponent.builder()
            .appComponent(MyApplication.appComponent)
            .loginModule(LoginModule())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.btn_sign_in.setOnClickListener { presenter.login(et_login.text.toString(), et_password.text.toString()) }
        view.btn_sign_up.setOnClickListener {
            presenter.register(
                et_login.text.toString(),
                et_password.text.toString()
            )
        }
        return view
    }
}
