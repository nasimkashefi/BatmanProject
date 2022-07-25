package com.example.batmanproject.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmDetail(
    val imdbID: String,
    val Year:String,
    val Title: String,
    val Rated: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language: String,
    val Country: String,
    val Awards: String,
    val Type: String,
    val Metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val Poster: String,
    val DVD: String,
    val BoxOffice: String,
    val Production: String,
    val Website: String,
    val Response: String,
    val Ratings: List<Rating>,
): Parcelable {
    @Parcelize
    data class Rating(
        val Source:String,
        val Value:String,
    ):Parcelable{}
}
