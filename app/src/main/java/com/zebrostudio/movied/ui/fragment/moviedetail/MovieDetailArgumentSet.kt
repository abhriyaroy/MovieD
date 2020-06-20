package com.zebrostudio.movied.ui.fragment.moviedetail

import com.zebrostudio.movied.data.entity.MovieEntity

data class MovieDetailArgumentSet(
    val previousMovieUrl: String,
    val nextMovieUrl: String,
    val selectedMovieItem: MovieEntity
)