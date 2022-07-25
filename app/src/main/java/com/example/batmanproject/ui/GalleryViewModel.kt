package com.example.batmanproject.ui

import android.os.Build
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.batmanproject.data.Film
import com.example.batmanproject.data.FilmRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: FilmRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    val films = currentQuery.switchMap { queryString ->
        repository.getResult(queryString).cachedIn(viewModelScope)
    }
    fun setCurrentQuery (query: String){
        currentQuery.value = query
    }
    companion object{
        private val DEFAULT_QUERY = "3e974fca"
    }
}