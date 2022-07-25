package com.example.batmanproject.data

import android.util.Log
import androidx.paging.PagingSource
import com.example.batmanproject.api.BatmanApi
import retrofit2.HttpException
import java.io.IOException

private const val FILM_STARTING_PAGE_INDEX = 1
class FilmPagingSource(
    private val FILMApi: BatmanApi,
    private val query: String
): PagingSource<Int, Film>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val position = params.key?: FILM_STARTING_PAGE_INDEX
        val TAG = "FilmPagingSource"
        return try {
            Log.d(TAG, "load: getting films")
            val response = FILMApi.GetFillms("3e974fca","batman")//
            Log.d(TAG, "load: response: $response")
            val films = response.Search
            LoadResult.Page(
                data = films,
                prevKey = if (position == FILM_STARTING_PAGE_INDEX) null else position-1,
                nextKey = if (films.isEmpty()) null else position+1
            )
        }
        catch (exception: IOException){
            Log.d(TAG, "load: ioexception: $exception")
            return LoadResult.Error(exception) }
        catch (exception: HttpException){
            Log.d(TAG, "load: HttpException: $exception")
            return LoadResult.Error(exception) }
    }
}