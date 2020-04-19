package com.zebrostudio.movied.repositories.helpers

import com.google.gson.GsonBuilder
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel
import com.zebrostudio.movied.utils.BASE_URL
import com.zebrostudio.movied.utils.POPULAR_MOVIES_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetworkHelper {
    suspend fun getMovies(): Response<MoviesApiResponseModel>
}

class NetworkHelperImpl : NetworkHelper {

    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(NetworkCalls::class.java)
    }

    override suspend fun getMovies(): Response<MoviesApiResponseModel> {
        return webService.getPopularMovies()
    }
}

interface NetworkCalls {
    @GET(POPULAR_MOVIES_URL)
    suspend fun getPopularMovies(): Response<MoviesApiResponseModel>
}