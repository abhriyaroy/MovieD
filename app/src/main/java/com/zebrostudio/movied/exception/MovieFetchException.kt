package com.zebrostudio.movied.exception

class MovieFetchException(private val errorMessage: String?) : Exception(errorMessage)