package com.zebrostudio.movied.repositories

import androidx.lifecycle.MutableLiveData
import com.zebrostudio.movied.repositories.helpers.NetworkHelper
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel

interface MovieDataRepository {
    suspend fun getPopularMovies(): MutableLiveData<MoviesApiResponseModel>
}

class MovieDataRepositoryImpl(private val networkHelper: NetworkHelper) :
    MovieDataRepository {

    override suspend fun getPopularMovies(): MutableLiveData<MoviesApiResponseModel> {
        val data = MutableLiveData<MoviesApiResponseModel>()
        val response = networkHelper.getMovies()
        if (response.isSuccessful) {
            data.value = response.body()!!
        } else {
            throw Error()
        }
        return data
    }
}