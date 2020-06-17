package com.zebrostudio.movied.util


const val BASE_URL = "https://api.themoviedb.org/3/"
const val POPULAR_MOVIES_URL =
    "discover/movie?sort_by=popularity.desc&api_key=$API_KEY"

fun getMoviePosterUrl(fileName: String) = "https://image.tmdb.org/t/p/original$fileName"