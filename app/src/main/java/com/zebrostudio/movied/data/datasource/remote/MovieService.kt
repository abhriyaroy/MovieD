package com.zebrostudio.movied.data.datasource.remote

import com.zebrostudio.movied.data.entity.MoviesResultEntity
import com.zebrostudio.movied.util.POPULAR_MOVIES_URL
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET(POPULAR_MOVIES_URL)
    suspend fun getPopularMovies(): Response<MoviesResultEntity>
}