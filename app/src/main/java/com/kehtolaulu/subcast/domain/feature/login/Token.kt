package com.kehtolaulu.subcast.domain.feature.login

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
class Token {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var token: String? = null
}