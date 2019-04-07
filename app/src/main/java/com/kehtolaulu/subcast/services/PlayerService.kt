package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.EpisodesDatabase
import com.kehtolaulu.subcast.entities.Episode

class PlayerService {
    private var dao: EpisodeDao? = null

    init {
        MyApplication.appComponent?.provideContext().let {
            it?.let { it1 -> EpisodesDatabase.getInstance(it1).getEpisodeDao() }
        }
    }

    fun getEpisode(id: Int): Episode? =
    //        return dao?.getEpisodeById(id)
        Episode(0, "hello", "description", "url", "path", 0)
}
