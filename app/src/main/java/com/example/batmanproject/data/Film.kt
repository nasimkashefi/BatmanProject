package com.example.batmanproject.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val imdbID: String,
    val Year:String,
    val Title: String,
    val Type: String,
    val Poster: String,
): Parcelable{}