package com.zebrostudio.movied.data.datasource.remote

import com.zebrostudio.movied.data.entity.MoviesResponseEntity
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(): Response<MoviesResponseEntity>
}

class MoviesRemoteDataSourceImpl(private val movieService: MovieService) :
    MoviesRemoteDataSource {

    override suspend fun getPopularMovies(): Response<MoviesResponseEntity> {
        return movieService.getPopularMovies()
    }
}