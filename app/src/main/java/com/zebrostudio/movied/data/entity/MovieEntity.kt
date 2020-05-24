package com.zebrostudio.movied.data.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("vote_count")
    var voteCount: Int,
    @SerializedName("video")
    var video: Boolean,
    @SerializedName("poster_path")
    var posterUrl: String,
    @SerializedName("id")
    var id: Long,
    @SerializedName("adult")
    var isAdult: Boolean,
    @SerializedName("backdrop_path")
    var backDropUrl: String,
    @SerializedName("original_language")
    var originLanguage: String,
    @SerializedName("original_title")
    var originalName: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var averageVote: Double,
    @SerializedName("overview")
    var summary: String,
    @SerializedName("release_date")
    var releaseDate: String
)