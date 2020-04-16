package com.zebrostudio.movied.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.zebrostudio.movied.repositories.models.MoviesApiResponseModel
import com.zebrostudio.movied.screens.main.MainContract
import com.zebrostudio.movied.screens.main.MainContract.MainPresenter
import com.zebrostudio.movied.usecases.MovieType
import com.zebrostudio.movied.usecases.MoviesUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainPresenterImpl(private val moviesUseCase: MoviesUseCase) : MainPresenter {

    override fun handleViewCreated(lifecycleOwner: LifecycleOwner) {
        CoroutineScope(IO).launch {
            Log.d("getNewsResponse", "From IO Context ${Thread.currentThread().name}")
            Log.d("hello", moviesUseCase.getMovies(MovieType.POPULAR).value.toString())
            Log.d("getNewsResponse", "From IO Context 2: ${Thread.currentThread().name}")
        }
    }


}