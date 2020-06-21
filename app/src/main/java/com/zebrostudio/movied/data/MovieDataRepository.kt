package com.zebrostudio.movied.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zebrostudio.movied.data.datasource.remote.MoviesRemoteDataSource
import com.zebrostudio.movied.data.entity.MoviesResultEntity
import com.zebrostudio.movied.exception.MovieFetchException
import com.zebrostudio.movied.exception.NoInternetException
import com.zebrostudio.movied.util.ResourceResult
import java.net.UnknownHostException


interface MovieDataRepository {
    suspend fun getPopularMovies(): LiveData<ResourceResult<MoviesResultEntity>>
}

class MovieDataRepositoryImpl(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    MovieDataRepository {

    override suspend fun getPopularMovies(): LiveData<ResourceResult<MoviesResultEntity>> {
        return MutableLiveData<ResourceResult<MoviesResultEntity>>().let { mutableLiveData ->
            try {
                with(moviesRemoteDataSource.getPopularMovies()) {
                    if (isSuccessful) {
                        mutableLiveData.value = ResourceResult.success(body()!!)
                    } else {
                        mutableLiveData.value =
                            ResourceResult.error(MovieFetchException())
                    }
                }
            } catch (e: UnknownHostException) {
                mutableLiveData.value = ResourceResult.error(NoInternetException())
            }
            mutableLiveData
        }
    }
}