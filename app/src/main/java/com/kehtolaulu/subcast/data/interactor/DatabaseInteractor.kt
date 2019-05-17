package com.kehtolaulu.subcast.data.interactor

import com.kehtolaulu.subcast.data.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DatabaseInteractor(private val db: AppDatabase) {
    fun clearData(): Completable {
        Completable.fromAction { db.getPodcastDao().clearTable() }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
        return Completable.fromAction { db.clearAllTables() }
    }
}
