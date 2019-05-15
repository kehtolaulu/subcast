package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.api.ItunesApi
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.PodcastDao
import com.kehtolaulu.subcast.entities.Podcast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PodcastsService(private var itunesApi: ItunesApi, private var dao: PodcastDao, private var subcastApi: SubcastApi) {
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
}
