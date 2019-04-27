package com.kehtolaulu.subcast.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.facebook.stetho.Stetho
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.adapters.EpisodesAdapter
import com.kehtolaulu.subcast.adapters.PodcastsAdapter
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    EpisodesAdapter.EpisodeOnClickListener, PodcastsAdapter.PodcastOnClickListener {
    override fun onClick(podcast: Podcast) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var listener: OnQueryTextListener? = null

    override fun onClick(episode: Episode) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("id", episode.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigation.setupWithNavController(navController)
        Stetho.initializeWithDefaults(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchViewItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = "Search podcasts"
        searchView.setOnSearchClickListener { doSearchFragmentTransaction() }
        //TODO переписать под RX
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

    private fun doSearchFragmentTransaction() {
        val fragmentManager = supportFragmentManager
        val fragment = SearchFragment()
        listener = fragment
        fragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    interface OnQueryTextListener {
        fun onQueryTextChanged(query: String)
    }
}
