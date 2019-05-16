package com.kehtolaulu.subcast.presentation.feature.sync.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.data.interactor.*
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.presentation.feature.sync.view.SyncView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class SyncPresenter(
    private val tokenInteractor: TokenInteractor,
    private val syncInteractor: SyncInteractor,
    private val podcastsInteractor: PodcastsInteractor,
    private val episodesInteractor: EpisodesInteractor,
    private val interactor: DatabaseInteractor,
    private val rssInteractor: RssInteractor
) :
    MvpPresenter<SyncView>() {

    private val compositeDisposable = CompositeDisposable()

    fun signOut() {
        tokenInteractor.deleteToken()
        val disposable = interactor.clearData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.showSuccess() }, { viewState.showError("error when clearing data") })
        viewState.setLoginFragment()
        compositeDisposable.add(disposable)
    }

    private fun getPodcastById(id: Int): Single<Podcast?> {
        return podcastsInteractor.getPodcastById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sync() {
        val disposable1 = syncInteractor.syncSubscriptions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.subscriptions?.forEach {
                    getPodcastById(it.id).subscribe { podcast ->
                        podcast?.let { it1 -> podcastsInteractor.insertPodcast(it1) }
                    }
                }
                viewState.showSuccess()
            },
                { viewState.showError("no internet") })

        val disposable2 = syncInteractor.syncLater().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.list?.forEach { episode ->
                    episodesInteractor
                        .getEpisodeById(episode.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { episodeById ->
                            episode.podcastId?.let { id ->
                                getPodcastById(id).subscribe { podcast ->
                                    if (podcast != null) {
                                        rssInteractor.getPodcastEpisodes(podcast)?.callback(object : Callback {
                                            override fun onLoadFailed() {
                                                println("failed")
                                            }

                                            override fun onPreload() {
                                                println("preload")
                                            }

                                            override fun onLoaded(newArticles: MutableList<Article>?) {
                                                println("loaded")
                                                newArticles?.filter { article ->
                                                    episodeById.id == (article.author + article.title).hashCode().toString()
                                                }?.map { _ -> episodesInteractor.listenLater(episodeById) }
                                            }
                                        })?.async()
                                    }
                                }
                            }
                        }
                }
                viewState.showSuccess()
            },
                { viewState.showError("no internet") })

        val disposable3 = syncInteractor.syncProgress().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.progress?.forEach(
                    episodesInteractor::insertEpisode
                )
                viewState.showSuccess()
            }, { viewState.showError("no internet") })
        compositeDisposable.add(disposable1)
        compositeDisposable.add(disposable2)
        compositeDisposable.add(disposable3)
    }

    override fun detachView(view: SyncView?) {
        compositeDisposable.clear()
        super.detachView(view)
    }
}
