package com.kehtolaulu.subcast.database

import androidx.room.*
import com.kehtolaulu.subcast.entities.Episode
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEpisode(episode: Episode)

    @Delete
    fun deleteEpisode(episode: Episode)

    @Query("SELECT * FROM episode")
    fun getAllEpisodes(): Single<List<Episode>>

    @Query("SELECT * FROM episode WHERE id = :id")
    fun getEpisodeById(id: Int) : Single<Episode>

    // Downloads:

    @Query("SELECT * FROM episode WHERE path IS NOT NULL AND id = :id")
    fun getDownloadById(id: Int) : Single<Episode>

    @Query("SELECT * FROM episode WHERE path IS NOT NULL")
    fun getAllDownloads() : Single<List<Episode>>

    @Delete
    fun deleteDownload(episode: Episode)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDownload(episode: Episode)
}
