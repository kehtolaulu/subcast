package com.kehtolaulu.subcast.data.interactor

import com.kehtolaulu.subcast.data.database.EpisodeDao
import com.kehtolaulu.subcast.data.database.PodcastDao
import com.kehtolaulu.subcast.data.network.SubcastApi
import com.kehtolaulu.subcast.domain.feature.favourites.SubscriptionsResponse
import com.kehtolaulu.subcast.domain.feature.playlater.ListenLaterResponse
import com.kehtolaulu.subcast.domain.feature.progress.ProgressResponse
import io.reactivex.Single

class SyncInteractor(
    private val subcastApi: SubcastApi,
    private val episodeDao: EpisodeDao,
    private val podcastDao: PodcastDao
) {
    fun syncSubscriptions(): Single<SubscriptionsResponse> {
        return subcastApi.getSubscriptions()
    }

    fun syncLater(): Single<ListenLaterResponse> {
        return subcastApi.getPlayLater()
    }

    fun syncProgress(): Single<ProgressResponse> {
        return subcastApi.getAllProgress()
    }
}
