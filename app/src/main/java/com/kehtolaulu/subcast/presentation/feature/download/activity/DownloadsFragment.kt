package com.kehtolaulu.subcast.presentation.feature.download.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.di.components.DaggerDownloadComponent
import com.kehtolaulu.subcast.di.modules.DownloadModule
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.presentation.feature.download.adapter.DownloadsAdapter
import com.kehtolaulu.subcast.presentation.feature.download.presenter.DownloadsPresenter
import com.kehtolaulu.subcast.presentation.feature.download.view.DownloadsView
import com.kehtolaulu.subcast.presentation.feature.main.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_downloads.view.*
import javax.inject.Inject

class DownloadsFragment : MvpAppCompatFragment(),
    DownloadsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DownloadsPresenter

    @ProvidePresenter
    fun providePresenter(): DownloadsPresenter = presenter

    private var adapter: DownloadsAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun updateAdapter() {
        presenter.updateAdapter()
    }

    override fun submitListIntoAdapter(list: List<Episode>) {
        adapter?.submitList(list as MutableList<Episode>)
    }

    override fun addElementsToAdapter(list: List<Episode>) {
        val episodes: ArrayList<Episode> = ArrayList()
        adapter?.getList()?.let { episodes.addAll(it) }
        episodes.addAll(episodes)
        adapter?.submitList(episodes)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.kehtolaulu.subcast.R.layout.fragment_downloads, container, false)
        adapter = DownloadsAdapter()
        val rv = view.recycler_downloads
        rv.adapter = adapter
        adapter?.listItemClickListener = activity as MainActivity
        rv.layoutManager = LinearLayoutManager(activity)
        presenter.updateAdapter()
        return view
    }

    private fun initDagger() {
        DaggerDownloadComponent.builder()
            .appComponent(MyApplication.appComponent)
            .downloadModule(DownloadModule())
            .build()
            .inject(this)
    }

    companion object {
        fun newInstance() = DownloadsFragment()
    }
}
