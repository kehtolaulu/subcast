package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.data.database.EpisodeDao
import com.kehtolaulu.subcast.data.network.SubcastApi
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.playlater.ListenLaterRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodesInteractor(
    private var dao: EpisodeDao,
    private var subcastApi: SubcastApi,
    private val downloader: DownloadingInteractor,
    private val tokenInteractor: TokenInteractor
) {
    fun getEpisodeById(id: String): Single<Episode> = dao.getEpisodeById(id.toInt())

    fun getDownloads(): Single<List<Episode>> = dao.getAllDownloads()

    fun getListenLater(): Single<List<Episode>> = dao.getListenLater()

    fun download(episode: Episode) {
        Single.fromCallable { dao.insertEpisode(episode) }.subscribeOn(Schedulers.io()).subscribe()
        val path: String = downloader.download(episode).toString()
        Single.fromCallable {
            dao.download(path, episode.id)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun listenLater(episode: Episode) {
        Single.fromCallable { dao.insertEpisode(episode) }.subscribeOn(Schedulers.io()).subscribe()
        subcastApi.addPlayLater(
            ListenLaterRequest(
                tokenInteractor.getToken(),
                episode.id,
                episode.podcastId
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
        Single.fromCallable { dao.listenLater(episode.id) }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun insertEpisode(episode: Episode) {
        Single.fromCallable { dao.insertEpisode(episode) }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun saveProgress(episode: Episode, time: Int) {
        Single.fromCallable { dao.insertEpisode(episode) }.subscribeOn(Schedulers.io()).subscribe()
        Single.fromCallable { dao.setProgress(episode.id, time) }.subscribeOn(Schedulers.io()).subscribe()
    }

}
