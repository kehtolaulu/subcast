package com.kehtolaulu.subcast.database

import androidx.room.*
import com.kehtolaulu.subcast.domain.feature.details.Episode
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
    @Query("UPDATE episode SET path = :path WHERE id = :id")
    fun download(path: String, id: String)

    @Query("SELECT * FROM episode WHERE path IS NOT NULL AND id = :id")
    fun getDownloadById(id: Int) : Single<Episode>

    @Query("SELECT * FROM episode WHERE path IS NOT NULL")
    fun getAllDownloads() : Single<List<Episode>>

    //Listen Later:
    @Query("UPDATE episode SET isListenLater = 1 WHERE id = :id")
    fun listenLater(id: String)

    @Query("SELECT * FROM episode WHERE isListenLater = 1")
    fun getListenLater() : Single<List<Episode>>

    @Query("UPDATE episode SET progress = :time WHERE id = :id")
    fun setProgress(id: String, time: Int)
}
