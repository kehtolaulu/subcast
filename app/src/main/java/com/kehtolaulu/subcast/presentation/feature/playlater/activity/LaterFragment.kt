package com.kehtolaulu.subcast.presentation.feature.playlater.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.di.components.DaggerLaterComponent
import com.kehtolaulu.subcast.di.modules.LaterModule
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.presentation.extensions.showToast
import com.kehtolaulu.subcast.presentation.feature.main.activity.MainActivity
import com.kehtolaulu.subcast.presentation.feature.playlater.adapter.LaterAdapter
import com.kehtolaulu.subcast.presentation.feature.playlater.presenter.LaterPresenter
import com.kehtolaulu.subcast.presentation.feature.playlater.view.LaterView
import kotlinx.android.synthetic.main.fragment_downloads.view.*
import javax.inject.Inject

class LaterFragment : Fragment(), LaterView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LaterPresenter

    @ProvidePresenter
    fun providePresenter(): LaterPresenter = presenter

    private var adapter: LaterAdapter? = null

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
        activity?.showToast(error.message.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        DaggerLaterComponent.builder()
            .appComponent(MyApplication.appComponent)
            .laterModule(LaterModule())
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.kehtolaulu.subcast.R.layout.fragment_downloads, container, false)
        adapter = LaterAdapter()
        val rv = view.recycler_downloads
        rv.adapter = adapter
        adapter?.listItemClickListener = activity as MainActivity
        rv.layoutManager = LinearLayoutManager(activity)
        presenter.updateAdapter()
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LaterFragment()
    }
}
