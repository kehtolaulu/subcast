package com.kehtolaulu.subcast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.presenters.DownloadsPresenter
import com.kehtolaulu.subcast.services.DownloadsService
import com.kehtolaulu.subcast.views.DownloadsView
import kotlinx.android.synthetic.main.fragment_downloads.view.*

class DownloadsFragment : MvpAppCompatFragment(), DownloadsView{

    @InjectPresenter
    lateinit var presenter: DownloadsPresenter

    @ProvidePresenter
    fun initPresenter(): DownloadsPresenter =
        DownloadsPresenter(DownloadsService())

    private var adapter: DownloadsAdapter? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_downloads, container, false)
        adapter = DownloadsAdapter()
        val rv = view.recycler_downloads
        rv.adapter = adapter
        adapter?.listItemClickListener = activity as MainActivity
        rv.layoutManager = LinearLayoutManager(activity)
        presenter.updateAdapter()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = DownloadsFragment()
    }
}
