package com.ciandt.book.seeker.di

import android.content.Context
import androidx.room.Room
import com.ciandt.book.seeker.data.db.AppDatabase
import com.ciandt.book.seeker.data.db.dao.LastSearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "books"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLastSearchDao(db: AppDatabase): LastSearchDao {
        return db.lastSearchDao()
    }
}