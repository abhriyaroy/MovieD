package com.zebrostudio.movied.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zebrostudio.movied.data.datasource.remote.MoviesRemoteDataSource
import com.zebrostudio.movied.data.entity.MoviesResponseEntity
import com.zebrostudio.movied.viewmodel.ResourceResult
import java.net.UnknownHostException


interface MovieDataRepository {
    suspend fun getPopularMovies(): LiveData<ResourceResult<MoviesResponseEntity>>
}

class MovieDataRepositoryImpl(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    MovieDataRepository {

    override suspend fun getPopularMovies(): LiveData<ResourceResult<MoviesResponseEntity>> {
        return MutableLiveData<ResourceResult<MoviesResponseEntity>>().let { mutableLiveData ->
            try {
                with(moviesRemoteDataSource.getPopularMovies()) {
                    if (isSuccessful) {
                        mutableLiveData.value = ResourceResult.success(body()!!)
                    } else {
                        mutableLiveData.value =
                            ResourceResult.error(errorBody().toString())
                    }
                }
            } catch (e: UnknownHostException) {
                e.printStackTrace()
                mutableLiveData.value = ResourceResult.error(e.message)
            }
            mutableLiveData
        }
    }
}