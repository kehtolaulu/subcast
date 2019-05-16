package com.kehtolaulu.subcast.domain.feature.details

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "episode")
data class Episode(
    @SerializedName("guid")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var id: String,
    var name: String? = null,
    var description: String? = null,
    @SerializedName("link")
    var url: String? = null,
    var duration: Int? = null,
    var path: String? = null,
    @SerializedName("time")
    @Expose
    var progress: Int? = 0,
    @SerializedName("accountId")
    var accountId: Int? = null,
    var isListenLater: Boolean? = null,
    @SerializedName("podcastId")
    var podcastId: Int? = null
) : Parcelable
