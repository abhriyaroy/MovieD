package com.zebrostudio.movied.data.datasource.remote

import com.zebrostudio.movied.data.entity.MoviesResultEntity
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(): Response<MoviesResultEntity>
}

class MoviesRemoteDataSourceImpl(private val movieService: MovieService) :
    MoviesRemoteDataSource {

    override suspend fun getPopularMovies(): Response<MoviesResultEntity> {
        return movieService.getPopularMovies()
    }
}