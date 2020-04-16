package com.zebrostudio.movied.repositories

import androidx.lifecycle.MutableLiveData
import com.zebrostudio.movied.repositories.helpers.NetworkHelper
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel
import okhttp3.Response

interface MovieDataRepository {
    suspend fun getPopularMovies(): MutableLiveData<MoviesApiResponseModel>
}

class MovieDataRepositoryImpl(private val networkHelper: NetworkHelper) :
    MovieDataRepository {

    val liveData by lazy {
        MutableLiveData<MoviesApiResponseModel>()
    }

    override suspend fun getPopularMovies(): MutableLiveData<MoviesApiResponseModel> {
        val response = networkHelper.getMovies()
        if (response.isSuccessful) {
            liveData.postValue(response.body())
        }
        return liveData
    }
}