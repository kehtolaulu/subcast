package com.kehtolaulu.subcast.presentation.feature.search.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.di.components.DaggerSearchComponent
import com.kehtolaulu.subcast.di.modules.SearchModule
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.presentation.feature.main.activity.MainActivity
import com.kehtolaulu.subcast.presentation.feature.search.adapter.PodcastsAdapter
import com.kehtolaulu.subcast.presentation.feature.search.presenter.SearchPresenter
import com.kehtolaulu.subcast.presentation.feature.search.view.SearchView
import kotlinx.android.synthetic.main.fragment_search.view.*
import javax.inject.Inject

class SearchFragment : MvpAppCompatFragment(), MainActivity.OnQueryTextListener,
    SearchView {

    var adapter: PodcastsAdapter? = null

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        DaggerSearchComponent.builder()
            .appComponent(MyApplication.appComponent)
            .searchModule(SearchModule())
            .build()
            .inject(this)
    }

    override fun submitListIntoAdapter(list: List<Podcast>) {
        adapter?.submitList(list as MutableList<Podcast>)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }

    var queryText: String? = null

    override fun onQueryTextChanged(query: String) {
        Log.i("Tag", query)
        queryText = query
        updateAdapterByQueryResult(query)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        adapter = PodcastsAdapter()
        val rv = view.recycler_search
        rv.adapter = adapter
        adapter?.listItemClickListener = activity as MainActivity
        rv.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun updateAdapterByQueryResult(query: String) {
        presenter.updateAdapter(query)
    }
}
