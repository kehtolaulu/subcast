package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.services.EpisodesService
import com.kehtolaulu.subcast.services.PodcastsService
import com.kehtolaulu.subcast.services.RssDealer
import com.kehtolaulu.subcast.views.DetailsView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class DetailsPresenter(
    private val episodesService: EpisodesService,
    private val podcastsService: PodcastsService,
    private val rssDealer: RssDealer
) :
    MvpPresenter<DetailsView>(), Callback {
    override fun onLoadFailed() {
        println("failed")
    }

    override fun onPreload() {
        println("preload")
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        println(newArticles?.get(0)?.enclosure?.url)
        newArticles?.map { toEpisode(it) }?.let { viewState.submitListIntoAdapter(it) }
//        updateAdapter(list)
    }

    private fun toEpisode(article: Article): Episode {
        return Episode(
            article.id.toString(),
            article.title,
            article.description,
            article.enclosure.url
        )
    }

    fun getPodcastEpisodes(podcast: Podcast) {
        podcast.feedUrl?.let { rssDealer.getPodcastEpisodes(it)?.callback(this)?.async() }
    }

    fun updateAdapter(podcast: Podcast) {
        getPodcastEpisodes(podcast)
    }

    fun getPodcast(id: Int?) {
        id?.let {
            podcastsService.getPodcastById(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it?.let { it1 -> viewState.showPodcast(it1) } }, viewState::showError)
        }
    }
}
