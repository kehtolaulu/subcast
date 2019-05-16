package com.kehtolaulu.subcast.data.interactor

import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.pkmmte.pkrss.PkRSS
import com.pkmmte.pkrss.RequestCreator

class RssInteractor (private var rss: PkRSS) {
    fun getPodcastEpisodes(podcast: Podcast): RequestCreator? {
        return rss.load(podcast.feedUrl)
    }
}
