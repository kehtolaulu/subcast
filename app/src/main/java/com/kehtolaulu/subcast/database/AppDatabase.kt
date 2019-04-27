package com.kehtolaulu.subcast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.entities.Podcast
import com.kehtolaulu.subcast.entities.Token

@Database(entities = [Episode::class, Podcast::class, Token::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEpisodeDao(): EpisodeDao

    abstract fun getPodcastDao(): PodcastDao

    abstract fun getTokenDao(): TokenDao

    companion object {
        const val DATABASE_NAME = "episodes_db"
    }
}
