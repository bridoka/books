package com.ciandt.book.seeker.data.respose

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookListResponse(
    @Json(name = "results")
    val results: List<BookListItemResponse>
)