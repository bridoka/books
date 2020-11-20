package com.ciandt.book.seeker.presentation.search

import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.functions.Function


class LastSearchesViewEntityMapper :
    Function<List<LastSearchStoreModel>, List<LastSearchViewEntity>> {
    override fun apply(t: List<LastSearchStoreModel>): List<LastSearchViewEntity> {
        return t.map {
            LastSearchViewEntity(
                id = it.id ?: 1,
                description = it.description
            )
        }
    }
}