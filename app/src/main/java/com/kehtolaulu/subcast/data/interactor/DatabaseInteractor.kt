package com.kehtolaulu.subcast.data.interactor

import com.kehtolaulu.subcast.data.database.AppDatabase
import io.reactivex.Completable

class DatabaseInteractor(private val db: AppDatabase) {
    fun clearData(): Completable {
        return Completable.fromAction{db.clearAllTables()}
    }
}
