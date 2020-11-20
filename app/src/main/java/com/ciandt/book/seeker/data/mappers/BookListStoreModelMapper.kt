package com.ciandt.book.seeker.data.mappers

import com.ciandt.book.seeker.data.respose.BookListResponse
import com.ciandt.book.seeker.data.store.model.BookListStoreModel
import io.reactivex.functions.Function

class BookListStoreModelMapper :
    Function<BookListResponse, List<BookListStoreModel>> {
    override fun apply(t: BookListResponse): List<BookListStoreModel> {
        return t.results.map {
            BookListStoreModel(
                author = it.artistName,
                title = it.trackName,
                urlBookImage = it.artworkUrl,
                urlPreview = it.trackViewUrl
            )
        }
    }
}