package com.kehtolaulu.subcast.services

import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.entities.Token

class TokenService (private var tokenDao: TokenDao) {
    fun getToken(): Token = tokenDao.selectToken()
}