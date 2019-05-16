package com.kehtolaulu.subcast.presentation.feature.player.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.EpisodesInteractor
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.presentation.extensions.asyncOnMainThread
import com.kehtolaulu.subcast.presentation.feature.player.view.PlayerView
import com.kehtolaulu.subcast.presentation.service.PlayerServiceConnection
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.delay

@InjectViewState
class PlayerPresenter(private val interactor: EpisodesInteractor, private val serviceConnection: PlayerServiceConnection) :
    MvpPresenter<PlayerView>() {

    private val compositeDisposable = CompositeDisposable()

    private var currentPosition: Int? = 0

    suspend fun isPlaying(): Boolean? {
        return serviceConnection.getPlayer().isPlaying()
    }

    fun checkProgress(episode: Episode): Single<Episode> {
        return interactor.getEpisodeById(episode.id)
    }

    fun showEpisode(episode: Episode) {
        viewState.showEpisode(episode)
        startUpdatingCurrentPosition()
    }

    suspend fun setList(list: List<Episode>) {
        serviceConnection.getPlayer().setEpisodeList(list)
    }

    private fun startUpdatingCurrentPosition() {
        asyncOnMainThread {
            currentPosition = serviceConnection.getPlayer().getCurrentPosition()
            viewState.updatePosition(currentPosition!!)
            delay(1000)
            startUpdatingCurrentPosition()
        }
    }

    fun seekTo(seconds: Int) {
        asyncOnMainThread {
            serviceConnection.getPlayer().seekTo(seconds)
        }
    }

    private suspend fun getActiveEpisode(): Episode? {
        return serviceConnection.getPlayer().getActiveEpisode()
    }

    fun playOrPauseEpisode(episode: Episode) {
        asyncOnMainThread {
            serviceConnection.getPlayer().playOrPauseEpisode(episode)
        }
    }

    fun playFromPosition(episode: Episode, time: Int) {
        asyncOnMainThread {
            serviceConnection.getPlayer().playFrom(episode, time)
        }
    }

    fun playOrPauseEpisode() {
        asyncOnMainThread {
            serviceConnection.getPlayer().playOrPauseEpisode()
        }
    }

    fun playNext() {
        asyncOnMainThread {
            serviceConnection.getPlayer().next()
            viewState.updatePlayingEpisode(getActiveEpisode())
        }
    }

    fun playPrevious() {
        asyncOnMainThread {
            serviceConnection.getPlayer().previous()
            viewState.updatePlayingEpisode(getActiveEpisode())
        }
    }

    suspend fun playingStatus(): Boolean? {
        return serviceConnection.getPlayer().isPlaying()
    }

    fun saveProgress() {
        asyncOnMainThread {
            serviceConnection.getPlayer().getActiveEpisode()?.let {
                interactor.saveProgress(
                    it,
                    serviceConnection.getPlayer().getCurrentPosition()
                )
            }
        }
    }

    override fun destroyView(view: PlayerView?) {
        compositeDisposable.clear()
        super.destroyView(view)
    }

}
