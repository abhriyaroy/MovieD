package com.zebrostudio.movied.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movied.R
import com.zebrostudio.movied.data.entity.MovieEntity
import com.zebrostudio.movied.util.ImageLoader

class MovieBannerListAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<ViewHolderBanner>() {

    private var moviesList: List<MovieEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBanner {
        return ViewHolderBanner(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_banner, parent, false),
            parent.context,
            imageLoader
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderBanner,
        position: Int
    ) {
        moviesList[position].let {
            holder.showImage(it.posterUrl)
        }
    }

    fun setList(list: List<MovieEntity>) {
        moviesList = list
        notifyDataSetChanged()
    }

}

class ViewHolderBanner(
    itemView: View,
    private val context: Context,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemView) {

    fun showImage(url: String) {
        imageLoader.loadImage(context, itemView.findViewById(R.id.movieBannerPoster), url)
    }

}