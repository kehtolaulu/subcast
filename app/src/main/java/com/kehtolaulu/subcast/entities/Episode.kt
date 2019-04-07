package com.kehtolaulu.subcast.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class Episode(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String? = null,
    var description: String? = null,
    var url: String,
    var path: String? = null,
    var progress: Int? = 0
)
