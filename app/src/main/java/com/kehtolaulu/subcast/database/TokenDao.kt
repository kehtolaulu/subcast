package com.kehtolaulu.subcast.database

import androidx.room.*
import com.kehtolaulu.subcast.entities.Token
import io.reactivex.Single

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToken(token: Token)

    @Query("SELECT * FROM token LIMIT 1")
    fun selectToken() : Single<Token>

    @Delete
    fun deleteToken(token: Token)
}