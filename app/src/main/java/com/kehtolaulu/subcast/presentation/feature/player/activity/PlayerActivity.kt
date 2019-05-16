package com.kehtolaulu.subcast.presentation.feature.player.activity

import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.widget.SeekBar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.di.components.DaggerPlayerComponent
import com.kehtolaulu.subcast.di.modules.PlayerModule
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.presentation.extensions.asyncOnMainThread
import com.kehtolaulu.subcast.presentation.extensions.showToast
import com.kehtolaulu.subcast.presentation.extensions.toMinutesSecondsFormat
import com.kehtolaulu.subcast.presentation.feature.player.presenter.PlayerPresenter
import com.kehtolaulu.subcast.presentation.feature.player.view.PlayerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_player.*
import javax.inject.Inject

class PlayerActivity : MvpAppCompatActivity(), PlayerView {

    @Inject
    @InjectPresenter
    lateinit var presenter: PlayerPresenter

    @ProvidePresenter
    fun providePresenter(): PlayerPresenter = presenter

    var episodes: List<Episode>? = null
    var index: Int? = 0

    override fun updatePlayingEpisode(episode: Episode?) {
        episode?.let { presenter.showEpisode(it) }
    }

    override fun updatePosition(currentPosition: Int) {
        seekBar.progress = currentPosition
        tv_progress.text = currentPosition.toMinutesSecondsFormat()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        var episode: Episode = intent.getParcelableExtra("episode")
        val list = intent.getParcelableArrayListExtra<Episode>("list")
        episodes = list
        setList(list)
        presenter.showEpisode(episode)
        val checkProgress =
            presenter.checkProgress(episode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    if (it.progress != null) {
                        presenter.playFromPosition(it, it.progress!!)
                    } else {
                        presenter.playOrPauseEpisode(episode)
                    }
                }
        index = episodes?.indexOf(episode)
        btn_next.setOnClickListener {
            presenter.playNext()
        }
        btn_play.setImageResource(R.drawable.pause)
        btn_play.setOnClickListener {
            presenter.playOrPauseEpisode()
            asyncOnMainThread {
                if (presenter.playingStatus() == true) {
                    btn_play.setImageResource(R.drawable.pause)
                } else {
                    btn_play.setImageResource(R.drawable.play)
                }
            }
            presenter.saveProgress()
        }
        btn_prev.setOnClickListener { presenter.playPrevious() }
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {}

                override fun onStartTrackingTouch(seekBar: SeekBar) {}

                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) presenter.seekTo(progress)
                }
            })
        seekBar.max = duration(episode)
    }

    private fun setList(list: List<Episode>) {
        asyncOnMainThread {
            presenter.setList(list)
        }
    }

    override fun showEpisode(episode: Episode) {
        setEpisode(episode)
    }

    private fun setEpisode(episode: Episode) {
        tv_name.text = episode.name
        tv_time.text = duration(episode).toMinutesSecondsFormat()
    }

    private fun initDagger() {
        DaggerPlayerComponent.builder()
            .appComponent(MyApplication.appComponent)
            .playerModule(PlayerModule())
            .build()
            .inject(this)
    }

    override fun showError(error: Throwable) {
        showToast(error.message.toString())
    }

    fun duration(episode: Episode): Int {
        return MediaMetadataRetriever().let {
            it.setDataSource(episode.url, HashMap<String, String>())
            val dur = it.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            dur.toInt() / 1000
        }
    }
}
