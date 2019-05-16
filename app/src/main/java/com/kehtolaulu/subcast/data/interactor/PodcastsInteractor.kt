package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.data.network.ItunesApi
import com.kehtolaulu.subcast.data.network.SubcastApi
import com.kehtolaulu.subcast.data.database.PodcastDao
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.domain.feature.favourites.SubscriptionsRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PodcastsInteractor(
    private var itunesApi: ItunesApi,
    private var podcastDao: PodcastDao,
    private var subcastApi: SubcastApi,
    private var tokenInteractor: TokenInteractor
) {
    fun getPodcastsByName(query: String): Single<List<Podcast>> {
        return itunesApi.searchByName("podcast", query)
            .map {
                it.results ?: ArrayList(1)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPodcastById(id: Int): Single<Podcast?> {
        return itunesApi.searchById("podcast", id)
            .map {
                it.results?.get(0)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun subscribe(podcast: Podcast) {
        subcastApi.subscribe(
            SubscriptionsRequest(
                tokenInteractor.getToken(),
                podcast.id
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
        Single.fromCallable<Any> { podcastDao.insertPodcast(podcast) }.subscribeOn(Schedulers.io()).subscribe()
    }

    fun getFavourites(): Single<List<Podcast>> {
        return podcastDao.getAllPodcasts().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertPodcast(podcast: Podcast) {
        Single.fromCallable { podcastDao.insertPodcast(podcast) }.subscribeOn(Schedulers.io()).subscribe()
    }
}
