package com.zebrostudio.movied.usecases

import androidx.lifecycle.LiveData
import com.zebrostudio.movied.repositories.MovieDataRepository
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel
import com.zebrostudio.movied.usecases.MovieType.POPULAR
import kotlinx.coroutines.CoroutineScope

interface MoviesUseCase {
    suspend fun getMovies(movieType: MovieType): LiveData<MoviesApiResponseModel>
}

class MovieInteractor(private val movieRepository: MovieDataRepository) : MoviesUseCase {
    override suspend fun getMovies(movieType: MovieType): LiveData<MoviesApiResponseModel> {
        return when (movieType) {
            POPULAR -> {
                movieRepository.getPopularMovies()
            }
        }
    }
}

enum class MovieType {
    POPULAR
}