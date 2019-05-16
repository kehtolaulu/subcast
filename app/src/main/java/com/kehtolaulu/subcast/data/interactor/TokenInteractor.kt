package com.kehtolaulu.subcast.data.interactor

import android.content.Context
import com.kehtolaulu.subcast.data.database.TokenDao
import com.kehtolaulu.subcast.helpers.APP_PREFS
import com.kehtolaulu.subcast.helpers.APP_PREFS_TOKEN

class TokenInteractor(private var tokenDao: TokenDao, private var context: Context) {

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
