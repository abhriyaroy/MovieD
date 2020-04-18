package com.zebrostudio.movied.repositories

import com.zebrostudio.movied.repositories.helpers.NetworkHelper
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel

interface MovieDataRepository {
    suspend fun getPopularMovies(): MoviesApiResponseModel
}

class MovieDataRepositoryImpl(private val networkHelper: NetworkHelper) :
    MovieDataRepository {

    override suspend fun getPopularMovies(): MoviesApiResponseModel {
        val response = networkHelper.getMovies()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Error()
        }
    }
}