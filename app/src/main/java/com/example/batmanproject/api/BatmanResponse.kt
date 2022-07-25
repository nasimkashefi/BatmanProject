package com.example.batmanproject.api

import android.os.Parcelable
import com.example.batmanproject.data.Film
import kotlinx.android.parcel.Parcelize

data class BatmanResponse (
    val Search: List<Film>,
    val totalResults: Int,
    val Response: Boolean,
)
