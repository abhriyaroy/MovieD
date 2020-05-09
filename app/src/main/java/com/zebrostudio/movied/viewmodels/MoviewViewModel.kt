package com.zebrostudio.movied.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zebrostudio.movied.repositories.MovieDataRepository
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {

    private var moviesLiveData = MutableLiveData<MoviesApiResponseModel>()
    val moviesData: LiveData<MoviesApiResponseModel>
        get() = moviesLiveData


    fun getPopularMovies() {
        viewModelScope.launch {
            movieDataRepository.getPopularMovies().let {
                moviesLiveData.value = it
            }
        }
    }

}