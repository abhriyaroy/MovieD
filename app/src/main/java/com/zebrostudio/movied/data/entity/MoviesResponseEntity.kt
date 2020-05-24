package com.zebrostudio.movied.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesResponseEntity(
    @SerializedName("page")
    var page: String,
    @SerializedName("results")
    var moviesList: List<MovieEntity>
)