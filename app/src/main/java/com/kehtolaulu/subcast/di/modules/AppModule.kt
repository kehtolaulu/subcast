package com.kehtolaulu.subcast.di.modules

import android.content.Context
import androidx.room.Room
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.data.database.AppDatabase
import com.kehtolaulu.subcast.data.database.AppDatabase.Companion.DATABASE_NAME
import com.kehtolaulu.subcast.data.database.EpisodeDao
import com.kehtolaulu.subcast.data.database.PodcastDao
import com.kehtolaulu.subcast.data.database.TokenDao
import com.kehtolaulu.subcast.data.interactor.DatabaseInteractor
import com.kehtolaulu.subcast.data.interactor.RssInteractor
import com.pkmmte.pkrss.PkRSS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: MyApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideRssDealer(context: Context): RssInteractor =
        RssInteractor(PkRSS.with(context))

    @Provides
    @Singleton
    fun provideEpisodesDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideEpisodeDao(db: AppDatabase): EpisodeDao = db.getEpisodeDao()

    @Provides
    @Singleton
    fun provideTokenDao(db: AppDatabase): TokenDao = db.getTokenDao()

    @Provides
    @Singleton
    fun providePodcastDao(db: AppDatabase): PodcastDao = db.getPodcastDao()

    @Provides
    @Singleton
    fun provideDeleter(db: AppDatabase): DatabaseInteractor =
        DatabaseInteractor(db)

}
