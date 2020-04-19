package com.zebrostudio.movied.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zebrostudio.movied.repositories.MovieDataRepository
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {

    var moviesLiveData = MutableLiveData<MoviesApiResponseModel>()

    fun getPopularMovies() {
        viewModelScope.launch {
            movieDataRepository.getPopularMovies().let {
                moviesLiveData.value = it.value
            }
        }
    }

}