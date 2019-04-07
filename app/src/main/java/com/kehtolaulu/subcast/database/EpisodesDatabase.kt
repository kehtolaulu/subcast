package com.kehtolaulu.subcast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kehtolaulu.subcast.entities.Episode

@Database(entities = [Episode::class], version = 1, exportSchema = false)
abstract class EpisodesDatabase : RoomDatabase() {

    abstract fun getEpisodeDao(): EpisodeDao

    companion object {
        private const val DATABASE_NAME = "episodes_db"
        private var dbInstance: EpisodesDatabase? = null
        fun getInstance(context : Context): EpisodesDatabase {
            if(dbInstance == null){
                dbInstance = Room.databaseBuilder(context,
                    EpisodesDatabase::class.java, DATABASE_NAME).build()
            }
            return dbInstance as EpisodesDatabase
        }
    }
}
