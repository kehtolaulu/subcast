package com.kehtolaulu.subcast.ui

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.di.components.DaggerPlayerComponent
import com.kehtolaulu.subcast.di.modules.PlayerModule
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.presenters.PlayerPresenter
import com.kehtolaulu.subcast.views.PlayerView
import kotlinx.android.synthetic.main.content_player.*
import javax.inject.Inject

class PlayerActivity : MvpAppCompatActivity(), PlayerView {
    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: PlayerPresenter

    @ProvidePresenter
    fun providePresenter(): PlayerPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        presenter.showEpisode(intent.getParcelableExtra("episode"))
    }

    override fun showEpisode(episode: Episode) {
        setEpisode(episode)
    }

    private fun setEpisode(episode: Episode) {
        tv_name.text = episode.name
    }

    private fun initDagger() {
        DaggerPlayerComponent.builder()
            .appComponent(MyApplication.appComponent)
            .playerModule(PlayerModule())
            .build()
            .inject(this)
    }

}
