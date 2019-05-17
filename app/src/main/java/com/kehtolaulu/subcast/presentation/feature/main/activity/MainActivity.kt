package com.kehtolaulu.subcast.presentation.feature.main.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.facebook.stetho.Stetho
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.data.interactor.TokenInteractor
import com.kehtolaulu.subcast.di.components.DaggerMainComponent
import com.kehtolaulu.subcast.di.modules.MainModule
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.presentation.extensions.showToast
import com.kehtolaulu.subcast.presentation.feature.details.activity.DetailsFragment
import com.kehtolaulu.subcast.presentation.feature.details.adapter.EpisodesAdapter
import com.kehtolaulu.subcast.presentation.feature.download.activity.DownloadsFragment
import com.kehtolaulu.subcast.presentation.feature.download.adapter.DownloadsAdapter
import com.kehtolaulu.subcast.presentation.feature.favourites.activity.FavouritesFragment
import com.kehtolaulu.subcast.presentation.feature.favourites.adapter.FavouritesAdapter
import com.kehtolaulu.subcast.presentation.feature.login.activity.LoginFragment
import com.kehtolaulu.subcast.presentation.feature.main.presenter.MainPresenter
import com.kehtolaulu.subcast.presentation.feature.main.view.MainView
import com.kehtolaulu.subcast.presentation.feature.player.activity.PlayerActivity
import com.kehtolaulu.subcast.presentation.feature.playlater.activity.LaterFragment
import com.kehtolaulu.subcast.presentation.feature.playlater.adapter.LaterAdapter
import com.kehtolaulu.subcast.presentation.feature.search.activity.SearchFragment
import com.kehtolaulu.subcast.presentation.feature.search.adapter.PodcastsAdapter
import com.kehtolaulu.subcast.presentation.feature.sync.activity.SyncFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(),
    EpisodesAdapter.EpisodeOnClickListener, PodcastsAdapter.PodcastOnClickListener,
    DownloadsAdapter.DownloadOnClickListener, FavouritesAdapter.PodcastOnClickListener,
    MainView,
    LaterAdapter.OnClickListener {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    @Inject
    lateinit var tokenInteractor: TokenInteractor

    var listener: OnQueryTextListener? = null

    override fun showSuccess(message: String) {
        showToast(message)
    }

    override fun download(episode: Episode) {
        if (episode.url == null) {
            showSuccess(getString(R.string.download_error))
        }
        presenter.download(episode)
    }

    override fun listenLater(episode: Episode) {
        presenter.listenLater(episode)
    }

    override fun subscribe(podcast: Podcast) {
        if (tokenInteractor.getToken() == null) {
            showToast(getString(R.string.sign_in_plz))
        } else {
            presenter.subscribe(podcast)
        }
    }

    override fun onClick(podcast: Podcast) {
        setDetailsFragment(podcast)
    }

    override fun onClick(
        episode: Episode,
        episodesList: ArrayList<Episode>?
    ) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("episode", episode)
        intent.putParcelableArrayListExtra("list", episodesList)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_downloads -> {
                    val fragment = DownloadsFragment()
                    setFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favourites -> {
                    val fragment = FavouritesFragment()
                    setFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_later -> {
                    val fragment = LaterFragment()
                    setFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_account -> {
                    if (tokenInteractor.getToken() == null) {
                        val fragment = LoginFragment()
                        setFragment(fragment)
                    } else {
                        val fragment = SyncFragment()
                        setFragment(fragment)
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        Stetho.initializeWithDefaults(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_podcasts)
        searchView.setOnSearchClickListener {
            setSearchFragment()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) return true
                listener?.onQueryTextChanged(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) return true
                listener?.onQueryTextChanged(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setSearchFragment() {
        val fragmentManager = supportFragmentManager
        val fragment = SearchFragment()
        listener = fragment
        fragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun setDetailsFragment(podcast: Podcast) {
        val fragmentManager = supportFragmentManager
        val fragment = DetailsFragment.newInstance(podcast)
        fragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    fun setSyncFragment() {
        setFragment(SyncFragment())
    }

    fun setLoginFragment() {
        setFragment(LoginFragment())
    }

    private fun initDagger() {
        DaggerMainComponent.builder()
            .appComponent(MyApplication.appComponent)
            .mainModule(MainModule())
            .build()
            .inject(this)
    }

    interface OnQueryTextListener {
        fun onQueryTextChanged(query: String)
    }
}
