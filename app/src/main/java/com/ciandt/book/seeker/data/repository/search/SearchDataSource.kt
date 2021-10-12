package com.ciandt.book.seeker.data.repository.search

import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchDataSource {
    fun saveLastSearch(lastSearchStoreModel: LastSearchStoreModel): Completable

    fun getLastSearches(): Flowable<List<LastSearchStoreModel>>
}