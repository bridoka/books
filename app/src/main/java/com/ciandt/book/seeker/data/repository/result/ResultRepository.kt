package com.ciandt.book.seeker.data.repository.result

import com.ciandt.book.seeker.data.mappers.BookListStoreModelMapper
import com.ciandt.book.seeker.data.store.model.BookListStoreModel
import com.ciandt.book.seeker.network.ItunesBookService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ResultRepository @Inject constructor(
    val itunesBookService: ItunesBookService
) {
    fun searchBooks(term: String?): Single<List<BookListStoreModel>> {
        return itunesBookService
            .getBookList(term)
            .subscribeOn(Schedulers.io())
            .map(BookListStoreModelMapper())
    }
}