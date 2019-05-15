package com.kehtolaulu.subcast.di.modules

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.AppDatabase
import com.kehtolaulu.subcast.database.AppDatabase.Companion.DATABASE_NAME
import com.kehtolaulu.subcast.database.PodcastDao
import com.kehtolaulu.subcast.database.TokenDao
import com.kehtolaulu.subcast.di.scope.DetailsScope
import com.kehtolaulu.subcast.services.PlayerService
import com.kehtolaulu.subcast.services.PlayerServiceConnection
import com.kehtolaulu.subcast.services.RssDealer
import com.pkmmte.pkrss.PkRSS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideRssDealer(context: Context): RssDealer = RssDealer(PkRSS.with(context))

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

    @Singleton
    @Provides
    fun provideServiceConnection(): PlayerServiceConnection {
        return PlayerServiceConnection()
    }

    @Provides
    fun provideServiceIntent(app: Application): Intent {
        return Intent(app, PlayerService::class.java)
    }

}
