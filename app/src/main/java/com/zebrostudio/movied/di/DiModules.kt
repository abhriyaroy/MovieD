package com.zebrostudio.movied.di

import com.zebrostudio.movied.repositories.MovieDataRepository
import com.zebrostudio.movied.repositories.MovieDataRepositoryImpl
import com.zebrostudio.movied.repositories.helpers.NetworkHelper
import com.zebrostudio.movied.repositories.helpers.NetworkHelperImpl
import com.zebrostudio.movied.screens.main.MainContract.MainPresenter
import com.zebrostudio.movied.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single<NetworkHelper> { NetworkHelperImpl() }
    single<MovieDataRepository> { MovieDataRepositoryImpl(get()) }
    viewModel { MovieViewModel(get()) }
}
