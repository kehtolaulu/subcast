package com.kehtolaulu.subcast.services

import android.content.Context
import com.kehtolaulu.subcast.constants.APP_PREFS
import com.kehtolaulu.subcast.constants.APP_PREFS_TOKEN
import com.kehtolaulu.subcast.database.TokenDao

class TokenService(private var tokenDao: TokenDao, private var context: Context) {
//    fun getToken(): Single<Token> =
//        tokenDao.selectToken()

    fun getToken(): String? {
        return context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).getString(APP_PREFS_TOKEN, null)
    }

    fun saveToken(token: String) {
        context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).edit().putString(APP_PREFS_TOKEN, token).apply()
    }

    fun deleteToken() {
        context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE).edit().remove(APP_PREFS_TOKEN).apply()
    }
}
