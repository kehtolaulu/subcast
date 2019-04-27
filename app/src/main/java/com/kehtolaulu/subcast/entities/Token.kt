package com.kehtolaulu.subcast.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
class Token {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var token: String? = null
}