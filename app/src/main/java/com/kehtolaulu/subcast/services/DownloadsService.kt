package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.entities.Episode
import io.reactivex.Observable
import io.reactivex.Single

class DownloadsService(private var dao: EpisodeDao) {
    fun getDownloads(): Single<List<Episode>> {
        return dao.getAllDownloads()
    }
}
