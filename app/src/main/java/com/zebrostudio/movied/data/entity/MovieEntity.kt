package com.zebrostudio.movied.data.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("adult")
    val isAdult: Boolean,
    @SerializedName("backdrop_path")
    val backDropUrl: String,
    @SerializedName("original_language")
    val originLanguage: String,
    @SerializedName("original_title")
    val originalName: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val averageVote: Double,
    @SerializedName("overview")
    val summary: String,
    @SerializedName("release_date")
    val releaseDate: String
)