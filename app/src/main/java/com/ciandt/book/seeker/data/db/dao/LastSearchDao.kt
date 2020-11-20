package com.ciandt.book.seeker.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.Flowable

@Dao
interface LastSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lastSearchStoreModel: LastSearchStoreModel)

    @Query("SELECT * FROM last_search order by id desc limit 5")
    fun getLastFiveSearches(): Flowable<List<LastSearchStoreModel>>
}