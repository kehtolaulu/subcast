package com.kehtolaulu.subcast.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "podcast")
data class Podcast(
    @SerializedName("trackId")
    @Expose
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    @SerializedName("trackName")
    @Expose
    var name: String? = null,
    @SerializedName("feedUrl")
    @Expose
    var feedUrl: String? = null
)
