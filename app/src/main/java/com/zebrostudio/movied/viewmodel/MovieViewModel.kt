package com.zebrostudio.movied.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zebrostudio.movied.data.MovieDataRepository
import com.zebrostudio.movied.data.entity.MoviesResponseEntity
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {

    private var moviesLiveData = MutableLiveData<MoviesResponseEntity>()
    val moviesResponseData: LiveData<MoviesResponseEntity>
        get() = moviesLiveData


    fun getPopularMovies() {
        viewModelScope.launch {
            movieDataRepository.getPopularMovies().let {
                moviesLiveData.value = it
            }
        }
    }

}