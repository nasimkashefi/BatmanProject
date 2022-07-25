package com.example.batmanproject.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.batmanproject.api.BatmanApi
import com.example.batmanproject.data.FilmDetail
import com.example.batmanproject.data.FilmRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val repository: FilmRepository,
    val api: BatmanApi
) : ViewModel()  {
    private val filmDetailLiveData = MutableLiveData<FilmDetail>()
    val filmDetail: LiveData<FilmDetail> = filmDetailLiveData

    fun search(filmId: String) {
        viewModelScope.launch {
            val filmDetails = api.searchFilm("3e974fca",filmId)
            delay(2000)
            filmDetailLiveData.value = filmDetails
            filmDetailLiveData.postValue(filmDetails)
        }
    }

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    fun setCurrentQuery (query: String){
        currentQuery.value = query
    }
    companion object{
        private val DEFAULT_QUERY = "tt0372784"
    }


}