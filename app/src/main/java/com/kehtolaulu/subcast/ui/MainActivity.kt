package com.kehtolaulu.subcast.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.entities.Episode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DownloadsAdapter.ListItemClickListener {

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
    }
}
