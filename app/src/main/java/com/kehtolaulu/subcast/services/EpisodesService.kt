package com.kehtolaulu.subcast.services

import android.content.Context
import com.kehtolaulu.subcast.api.SubcastApi
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.entities.Episode
import com.pkmmte.pkrss.PkRSS
import com.pkmmte.pkrss.RequestCreator
import io.reactivex.Single
import javax.inject.Inject

class EpisodesService(private var dao: EpisodeDao, private var subcastApi: SubcastApi) {

    fun getEpisodeById(id: String): Single<Episode> = dao.getEpisodeById(id.toInt())
    fun getDownloads(): Single<List<Episode>> = dao.getAllDownloads()
}
