package com.kehtolaulu.subcast.presentation.feature.sync.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.di.components.DaggerSyncComponent
import com.kehtolaulu.subcast.di.modules.SyncModule
import com.kehtolaulu.subcast.presentation.extensions.showToast
import com.kehtolaulu.subcast.presentation.feature.main.activity.MainActivity
import com.kehtolaulu.subcast.presentation.feature.sync.presenter.SyncPresenter
import com.kehtolaulu.subcast.presentation.feature.sync.view.SyncView
import kotlinx.android.synthetic.main.fragment_sync.view.*
import java.sql.Timestamp
import javax.inject.Inject

class SyncFragment : MvpAppCompatFragment(), SyncView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SyncPresenter

    @ProvidePresenter
    fun providePresenter(): SyncPresenter = presenter

    override fun showError(message: String) {
        activity?.showToast(message)
    }

    override fun showSuccess(message: String) {
        activity?.showToast(message)
    }

    override fun setLoginFragment() {
        val activity = activity as MainActivity
        activity.setLoginFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        DaggerSyncComponent.builder()
            .appComponent(MyApplication.appComponent)
            .syncModule(SyncModule())
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.sync()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sync, container, false)
        view.btn_sign_out.setOnClickListener { presenter.signOut() }
        view.tv_sync.text = getString(R.string.last_sync_was, Timestamp(System.currentTimeMillis()).toString())
        return view
    }

}
