package com.kehtolaulu.subcast.database

import androidx.room.*
import com.kehtolaulu.subcast.domain.feature.search.Podcast
import io.reactivex.Single

@Dao
interface PodcastDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPodcast(podcast: Podcast)

    @Delete
    fun deletePodcast(podcast: Podcast)

    @Query("SELECT * FROM podcast")
    fun getAllPodcasts(): Single<List<Podcast>>

    @Query("SELECT * FROM podcast WHERE id = :id")
    fun getPodcastById(id: Int) : Single<Podcast>

}
