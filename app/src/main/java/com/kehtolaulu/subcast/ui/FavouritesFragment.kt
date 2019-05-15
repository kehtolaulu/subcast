package com.kehtolaulu.subcast.ui

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
import com.kehtolaulu.subcast.di.components.DaggerFavouritesComponent
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.presenters.FavouritesPresenter
import com.kehtolaulu.subcast.views.FavouritesView
import javax.inject.Inject

class FavouritesFragment : MvpAppCompatFragment(), FavouritesView {

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

    override fun updateAdapter() {
        presenter.updateAdapter()
    }

    override fun submitListIntoAdapter(list: List<Podcast>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addElementsToAdapter(list: List<Podcast>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: Throwable) {
        Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favourites, container, false)

    override fun onStart() {
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouritesFragment()
    }
}
