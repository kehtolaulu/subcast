package com.kehtolaulu.subcast.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.facebook.stetho.Stetho
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.adapters.EpisodesAdapter
import com.kehtolaulu.subcast.adapters.PodcastsAdapter
import com.kehtolaulu.subcast.di.components.DaggerMainComponent
import com.kehtolaulu.subcast.di.components.DaggerPlayerComponent
import com.kehtolaulu.subcast.di.modules.MainModule
import com.kehtolaulu.subcast.di.modules.PlayerModule
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.services.TokenService
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    EpisodesAdapter.EpisodeOnClickListener, PodcastsAdapter.PodcastOnClickListener {

    @Inject
    lateinit var tokenService: TokenService
    var listener: OnQueryTextListener? = null

    override fun onClick(podcast: Podcast) {
        setDetailsFragment(podcast)
    }

    override fun onClick(episode: Episode) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("episode", episode)
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
                R.id.navigation_queue -> {
                    val fragment = QueueFragment()
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
                    if (tokenService.getToken() == null) {
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
        searchView.queryHint = "Search podcasts"
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
