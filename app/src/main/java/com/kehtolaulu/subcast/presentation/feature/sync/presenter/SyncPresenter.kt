package com.kehtolaulu.subcast.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.services.*
import com.kehtolaulu.subcast.views.SyncView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class SyncPresenter(
    private val tokenService: TokenService,
    private val syncService: SyncService,
    private val podcastsService: PodcastsService,
    private val episodesService: EpisodesService,
    private val deleter: DatabaseDeleter,
    private val rssDealer: RssDealer
) :
    MvpPresenter<SyncView>() {

    private val compositeDisposable = CompositeDisposable()

    fun signOut() {
        tokenService.deleteToken()
        val disposable = deleter.clearData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.showSuccess() }, { viewState.showError("error when clearing data") })
        viewState.setLoginFragment()
        compositeDisposable.add(disposable)
    }

    private fun getPodcastById(id: Int): Single<Podcast?> {
        return podcastsService.getPodcastById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sync() {
        val disposable1 = syncService.syncSubscriptions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.subscriptions?.forEach {
                    getPodcastById(it.id).subscribe { podcast ->
                        podcast?.let { it1 -> podcastsService.insertPodcast(it1) }
                    }
                }
                viewState.showSuccess()
            },
                { viewState.showError("no internet") })

        val disposable2 = syncService.syncLater().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.list?.forEach { episode ->
                    episodesService
                        .getEpisodeById(episode.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { episodeById ->
                            episode.podcastId?.let { id ->
                                getPodcastById(id).subscribe { podcast ->
                                    if (podcast != null) {
                                        rssDealer.getPodcastEpisodes(podcast)?.callback(object : Callback {
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
                                                }?.map { _ -> episodesService.listenLater(episodeById) }
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

        val disposable3 = syncService.syncProgress().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.progress?.forEach(
                    episodesService::insertEpisode
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