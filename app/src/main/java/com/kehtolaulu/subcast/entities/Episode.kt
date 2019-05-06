package com.kehtolaulu.subcast.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "episode")
data class Episode(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var id: String,
    var name: String? = null,
    var description: String? = null,
    var url: String? = null,
    var path: String? = null,
    var progress: Int? = 0
) : Parcelable
