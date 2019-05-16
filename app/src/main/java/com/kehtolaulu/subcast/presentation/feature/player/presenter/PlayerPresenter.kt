package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.extensions.asyncOnMainThread
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.PlayerServiceConnection
import com.kehtolaulu.subcast.views.PlayerView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.delay

@InjectViewState
class PlayerPresenter(private val service: EpisodesService, private val serviceConnection: PlayerServiceConnection) :
    MvpPresenter<PlayerView>() {

    private val compositeDisposable = CompositeDisposable()

    private var currentPosition: Int? = 0

    suspend fun isPlaying(): Boolean? {
        return serviceConnection.getPlayer().isPlaying()
    }

    fun checkProgress(episode: Episode): Single<Episode> {
        var progress: Int? = null
        return service.getEpisodeById(episode.id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ it ->
//                if (it.progress != null) {
//                    progress = it.progress
//                }
//            }, { _ -> progress = null })
//        compositeDisposable.add(disposable)
//        return progress
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
                service.saveProgress(
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
