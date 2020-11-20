package com.ciandt.book.seeker.presentation.result

import com.ciandt.book.seeker.data.store.model.BookListStoreModel
import io.reactivex.functions.Function

class BookListViewEntityMapper :
    Function<List<BookListStoreModel>, List<BookListViewEntity>> {
    override fun apply(t: List<BookListStoreModel>): List<BookListViewEntity> {
        return t.map {
            BookListViewEntity(
                author = it.author,
                urlBookImage = it.urlBookImage,
                title = it.title
            )
        }
    }
}