package com.kehtolaulu.subcast.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.presenters.PlayerPresenter
import com.kehtolaulu.subcast.services.PlayerService
import com.kehtolaulu.subcast.views.PlayerView
import kotlinx.android.synthetic.main.content_player.*

class PlayerActivity : MvpAppCompatActivity(), PlayerView {
    @InjectPresenter
    lateinit var presenter: PlayerPresenter

    @ProvidePresenter
    fun initPresenter(): PlayerPresenter =
        PlayerPresenter(PlayerService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        presenter.getEpisode(intent.getIntExtra("id", 0))
    }

    override fun showEpisode(episode: Episode) {
        setEpisode(episode)
    }

    private fun setEpisode(episode: Episode) {
        tv_name.text = episode.name
    }
}
