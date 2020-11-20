package com.ciandt.book.seeker.data.repository.search

import com.ciandt.book.seeker.data.db.dao.LastSearchDao
import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.Flowable
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val lastSearchDao: LastSearchDao
) : SearchDataSource {

    override fun saveLastSearch(lastSearchStoreModel: LastSearchStoreModel) {
        lastSearchDao.insert(lastSearchStoreModel)
    }

    override fun getLastSearches(): Flowable<List<LastSearchStoreModel>> {
        return lastSearchDao.getLastFiveSearches()
    }
}