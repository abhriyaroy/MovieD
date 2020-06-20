package com.zebrostudio.movied.ui.fragment.moviedetail

import android.view.View

interface MovieItemClickCallback {
    fun handleClick(
        view: View,
        movieDetailArgumentSet: MovieDetailArgumentSet
    )
}
