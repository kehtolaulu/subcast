package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.EpisodesDatabase
import com.kehtolaulu.subcast.entities.Episode

class DownloadsService {
    private var dao : EpisodeDao? = null

    init {
        MyApplication.appComponent?.provideContext().let {
            it?.let { it1 -> EpisodesDatabase.getInstance(it1).getEpisodeDao() }
        }
    }

    fun getDownloads() : List<Episode>? = dao?.getAllDownloads()
//        return listOf(Episode(1, "hello", "description", "url"), Episode(2, "bye", "decsr2", "url2"),
//            Episode(3, "hey", "desc3", "url3"))
}
