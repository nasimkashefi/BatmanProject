package com.example.batmanproject.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.batmanproject.api.BatmanApi
import javax.inject.Inject


class FilmRepository @Inject constructor(private val batmanApi: BatmanApi) {

    fun getResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 100
            ),
            pagingSourceFactory = { FilmPagingSource(batmanApi, query) }
        ).liveData

}