package com.ciandt.book.seeker.presentation.search

import com.ciandt.book.seeker.data.repository.search.SearchRepository
import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.Flowable
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    val searchRepository: SearchRepository
) {

    fun saveLastSearch(searchText: String) {
        searchRepository.saveLastSearch(
            LastSearchStoreModel(
                id = null,
                description = searchText
            )
        )
    }

    fun getLastSearches(): Flowable<List<LastSearchViewEntity>> {
        return searchRepository
            .getLastSearches()
            .map(LastSearchesViewEntityMapper())
    }
}