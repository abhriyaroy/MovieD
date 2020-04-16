package com.zebrostudio.movied.di

import com.zebrostudio.movied.presenters.MainPresenterImpl
import com.zebrostudio.movied.repositories.MovieDataRepository
import com.zebrostudio.movied.repositories.MovieDataRepositoryImpl
import com.zebrostudio.movied.repositories.helpers.NetworkHelper
import com.zebrostudio.movied.repositories.helpers.NetworkHelperImpl
import com.zebrostudio.movied.screens.main.MainContract
import com.zebrostudio.movied.screens.main.MainContract.MainPresenter
import com.zebrostudio.movied.usecases.MovieInteractor
import com.zebrostudio.movied.usecases.MoviesUseCase
import org.koin.dsl.module
import org.koin.experimental.builder.factoryBy
import org.koin.experimental.builder.singleBy

val appModule = module {
    single<NetworkHelper> { NetworkHelperImpl() }
    single<MovieDataRepository> { MovieDataRepositoryImpl(get()) }
    single<MoviesUseCase> { MovieInteractor(get()) }
}

val mainModule = module {
    factory<MainPresenter> { MainPresenterImpl(get()) }
}