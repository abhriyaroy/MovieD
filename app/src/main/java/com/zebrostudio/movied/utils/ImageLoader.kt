package com.zebrostudio.movied.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

interface ImageLoader {
    fun loadImage(context: Context, imageView: ImageView, url: String)
}

class ImageLoaderImpl : ImageLoader {
    override fun loadImage(context: Context, imageView: ImageView, url: String) {
        with(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        ) {
            Glide.with(context)
                .load(getMoviePosterUrl(url))
                .apply(this)
                .into(imageView)
        }
    }
}