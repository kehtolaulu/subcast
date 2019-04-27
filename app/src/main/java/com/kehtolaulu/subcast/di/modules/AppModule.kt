package com.kehtolaulu.subcast.di.modules

import android.content.Context
import androidx.room.Room
import com.kehtolaulu.subcast.database.EpisodeDao
import com.kehtolaulu.subcast.database.AppDatabase
import com.kehtolaulu.subcast.database.AppDatabase.Companion.DATABASE_NAME
import com.kehtolaulu.subcast.database.TokenDao
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
    fun provideEpisodesDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideEpisodeDao(db: AppDatabase): EpisodeDao = db.getEpisodeDao()

    @Provides
    @Singleton
    fun provideTokenDao(db: AppDatabase): TokenDao = db.getTokenDao()

}
