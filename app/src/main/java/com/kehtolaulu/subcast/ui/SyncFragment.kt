package com.kehtolaulu.subcast.ui

import android.content.Context
import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.di.components.DaggerSearchComponent
import com.kehtolaulu.subcast.di.components.DaggerSyncComponent
import com.kehtolaulu.subcast.di.modules.SearchModule
import com.kehtolaulu.subcast.di.modules.SyncModule
import com.kehtolaulu.subcast.presenters.SyncPresenter
import com.kehtolaulu.subcast.views.SyncView
import kotlinx.android.synthetic.main.fragment_sync.view.*
import javax.inject.Inject

class SyncFragment : MvpAppCompatFragment(), SyncView {
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

    @Inject
    @InjectPresenter
    lateinit var presenter: SyncPresenter

    @ProvidePresenter
    fun providePresenter(): SyncPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sync, container, false)
        view.btn_sign_out.setOnClickListener { presenter.signOut() }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) {
        }
    }
}
