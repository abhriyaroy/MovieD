package com.zebrostudio.movied.di

import com.google.gson.GsonBuilder
import com.zebrostudio.movied.data.MovieDataRepository
import com.zebrostudio.movied.data.MovieDataRepositoryImpl
import com.zebrostudio.movied.data.datasource.remote.MovieService
import com.zebrostudio.movied.data.datasource.remote.MoviesRemoteDataSource
import com.zebrostudio.movied.data.datasource.remote.MoviesRemoteDataSourceImpl
import com.zebrostudio.movied.util.BASE_URL
import com.zebrostudio.movied.util.ImageLoader
import com.zebrostudio.movied.util.ImageLoaderImpl
import com.zebrostudio.movied.util.Serializer
import com.zebrostudio.movied.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule: Module = module {
    single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get()) }
    single<MovieDataRepository> { MovieDataRepositoryImpl(get()) }
    single<Serializer>()
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
    single<MovieService> {
        provideMovieService(get())
    }
}

val viewModelModule : Module = module {
    viewModel { MovieViewModel(get()) }
}

val uiModule : Module = module {
    single<ImageLoader> { ImageLoaderImpl() }
}

fun provideMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)
