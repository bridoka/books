package com.ciandt.book.seeker.presentation.result

import com.ciandt.book.seeker.data.repository.result.ResultRepository
import io.reactivex.Single
import javax.inject.Inject

class ResultInteractor @Inject constructor(
    private val resultRepository: ResultRepository
) {
    fun getBooksList(term: String?): Single<List<BookListViewEntity>> {
        return resultRepository
            .searchBooks(term)
            .map(BookListViewEntityMapper())
    }
}