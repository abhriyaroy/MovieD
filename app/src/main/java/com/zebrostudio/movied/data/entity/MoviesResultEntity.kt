package com.zebrostudio.movied.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesResultEntity(
    @SerializedName("page")
    val page: String,
    @SerializedName("results")
    val moviesList: List<MovieEntity>
)