package com.kehtolaulu.subcast.services

import com.pkmmte.pkrss.PkRSS
import com.pkmmte.pkrss.RequestCreator

class RssDealer (private var rss: PkRSS) {
//    PkRSS.with(context)
    fun getPodcastEpisodes(feedUrl: String): RequestCreator? {
        return rss.load(feedUrl)
    }
}
