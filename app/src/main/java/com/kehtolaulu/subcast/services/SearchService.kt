package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.api.SearchApi
import com.kehtolaulu.subcast.entities.Podcast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchService(val api: SearchApi) {
    fun getPodcastsByName(query: String): Single<List<Podcast>> {
        return api.search("podcast", query)
            .map {
                it.results ?: ArrayList(1)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
