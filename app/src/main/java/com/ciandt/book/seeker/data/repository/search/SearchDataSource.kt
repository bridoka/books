package com.ciandt.book.seeker.data.repository.search

import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.Flowable

interface SearchDataSource {
    fun saveLastSearch(lastSearchStoreModel: LastSearchStoreModel)

    fun getLastSearches(): Flowable<List<LastSearchStoreModel>>
}