package com.ciandt.book.seeker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ciandt.book.seeker.data.db.dao.LastSearchDao
import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel

@Database(entities = [LastSearchStoreModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lastSearchDao(): LastSearchDao
}