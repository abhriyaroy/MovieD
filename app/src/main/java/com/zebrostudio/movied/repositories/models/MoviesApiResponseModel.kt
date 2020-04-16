package com.zebrostudio.movied.repositories.models

import com.google.gson.annotations.SerializedName

data class MoviesApiResponseModel (
    @SerializedName("page")
    var page : String,
    @SerializedName("results")
    var moviesList : List<MovieItemModel>
)