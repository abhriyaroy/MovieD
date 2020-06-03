package com.zebrostudio.movied.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zebrostudio.movied.data.MovieDataRepository
import com.zebrostudio.movied.data.entity.MoviesResponseEntity
import com.zebrostudio.movied.viewmodel.Status.SUCCESS
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {

    private var moviesLiveData = MutableLiveData<Result<MoviesResponseEntity>>()
    val moviesResponseData: LiveData<Result<MoviesResponseEntity>>
        get() = moviesLiveData

    init {
        moviesLiveData.value = Result.loading()
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            movieDataRepository.getPopularMovies().let {
                moviesLiveData.value = Result.success(it)
            }
        }
    }

}