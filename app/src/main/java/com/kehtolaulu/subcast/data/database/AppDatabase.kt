package com.kehtolaulu.subcast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import com.kehtolaulu.subcast.domain.feature.login.Token

@Database(entities = [Episode::class, Podcast::class, Token::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEpisodeDao(): EpisodeDao

    abstract fun getPodcastDao(): PodcastDao

    abstract fun getTokenDao(): TokenDao

    companion object {
        const val DATABASE_NAME = "episodes_db"
    }
}
