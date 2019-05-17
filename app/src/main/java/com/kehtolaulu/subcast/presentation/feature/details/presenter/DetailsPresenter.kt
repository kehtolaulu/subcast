package com.kehtolaulu.subcast.presentation.feature.details.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.PodcastsInteractor
import com.kehtolaulu.subcast.data.interactor.RssInteractor
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.presentation.feature.details.view.DetailsView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class DetailsPresenter(
    private val podcastsInteractor: PodcastsInteractor,
    private val rssInteractor: RssInteractor
) :
    MvpPresenter<DetailsView>(), Callback {
    override fun onLoadFailed() {

    }

    override fun onPreload() {

    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        println(newArticles?.get(0)?.enclosure?.url)
        newArticles?.filter { article -> article.enclosure != null }?.map { toEpisode(it) }
            .let {
                if (it != null) {
                    viewState.submitListIntoAdapter(it)
                }
            }
    }

    private fun toEpisode(article: Article): Episode {
        return Episode(
            (article.author + article.title).hashCode().toString(),
            article.title,
            article.description,
            article.enclosure.url
        )
    }

    fun getPodcastEpisodes(podcast: Podcast) {
        rssInteractor.getPodcastEpisodes(podcast)?.callback(this)?.async()
    }

    fun updateAdapter(podcast: Podcast) {
        getPodcastEpisodes(podcast)
    }

    fun getPodcast(id: Int?) {
        id?.let {
            podcastsInteractor.getPodcastById(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it?.let { podcast -> viewState.showPodcast(podcast) } }, viewState::showError)
        }
    }
}
