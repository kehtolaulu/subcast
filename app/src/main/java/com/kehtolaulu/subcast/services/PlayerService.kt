package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.entities.Episode
import io.reactivex.Single

class PlayerService(private var dao: EpisodeDao) {
    fun getEpisode(id: Int): Single<Episode> = dao.getEpisodeById(id)
}
