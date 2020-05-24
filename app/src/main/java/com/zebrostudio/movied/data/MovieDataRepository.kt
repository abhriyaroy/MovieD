package com.zebrostudio.movied.data

import com.zebrostudio.movied.data.datasource.remote.MoviesRemoteDataSource
import com.zebrostudio.movied.data.entity.MoviesResponseEntity
import com.zebrostudio.movied.exception.MovieFetchException

interface MovieDataRepository {
    suspend fun getPopularMovies(): MoviesResponseEntity
}

class MovieDataRepositoryImpl(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    MovieDataRepository {

    override suspend fun getPopularMovies(): MoviesResponseEntity {
        val response = moviesRemoteDataSource.getPopularMovies()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw MovieFetchException(response.errorBody()?.string())
        }
    }
}