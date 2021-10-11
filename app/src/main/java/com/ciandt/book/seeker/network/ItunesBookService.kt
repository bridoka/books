package com.ciandt.book.seeker.network

import com.ciandt.book.seeker.data.respose.BookListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesBookService {
    @GET("search?entity=ibook&limit=20")
    fun getBookList(
        @Query("term") term: String?
    ): Single<BookListResponse>
}