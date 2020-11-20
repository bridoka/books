package com.ciandt.book.seeker.data.respose

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookListItemResponse(
    @Json(name = "trackName")
    val trackName: String,
    @Json(name = "artworkUrl100")
    val artworkUrl: String,
    @Json(name = "artistName")
    val artistName: String
)