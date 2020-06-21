package com.zebrostudio.movied.exception


private const val movieFetchExceptionMessage = "Something went wrong while fetching movies"

class MovieFetchException : Exception(movieFetchExceptionMessage)