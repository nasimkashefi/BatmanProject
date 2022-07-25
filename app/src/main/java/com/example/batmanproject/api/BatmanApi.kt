package com.example.batmanproject.api


import com.example.batmanproject.data.FilmDetail
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BatmanApi {
    companion object{
        const val BASE_URL = "https://www.omdbapi.com/"
    }

    @GET(".")
    suspend fun GetFillms(
        @Query("apikey") apiKey: String,
        @Query("s") s: String,
    ):BatmanResponse

    @GET(".")
    suspend fun searchFilm(
        @Query("apikey") apiKey: String,
        @Query("i") s: String,
    ):FilmDetail

}