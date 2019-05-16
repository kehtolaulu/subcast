package com.kehtolaulu.subcast.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.adapters.FavouritesAdapter
import com.kehtolaulu.subcast.di.components.DaggerFavouritesComponent
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.extensions.showToast
import com.kehtolaulu.subcast.presentation.feature.favourites.presenter.FavouritesPresenter
import com.kehtolaulu.subcast.presentation.feature.favourites.view.FavouritesView
import kotlinx.android.synthetic.main.fragment_favourites.view.*
import javax.inject.Inject

class FavouritesFragment : MvpAppCompatFragment(),
    FavouritesView {

    private var adapter: FavouritesAdapter? = null

    @Inject
    @InjectPresenter
    lateinit var presenter: FavouritesPresenter

    @ProvidePresenter
    fun providePresenter(): FavouritesPresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        DaggerFavouritesComponent.builder()
            .appComponent(MyApplication.appComponent)
            .build()
            .inject(this)
    }

    private fun updateAdapter() {
        presenter.updateAdapter()
    }

    override fun submitListIntoAdapter(list: List<Podcast>) {
        adapter?.submitList(list as MutableList<Podcast>)
    }

    override fun addElementsToAdapter(list: List<Podcast>) {
        val podcasts: ArrayList<Podcast> = ArrayList()
        adapter?.podcasts?.let { podcasts.addAll(it) }
        podcasts.addAll(podcasts)
        adapter?.submitList(podcasts)    }

    override fun showError(error: Throwable) {
        activity?.showToast(error.message.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.kehtolaulu.subcast.R.layout.fragment_favourites, container, false)
        adapter = FavouritesAdapter()
        val rv = view.recycler_favourites
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
        fun newInstance() = FavouritesFragment()
    }
}
