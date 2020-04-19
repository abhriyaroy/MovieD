package com.zebrostudio.movied.screens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movied.R
import com.zebrostudio.movied.repositories.models.MovieItemModel
import com.zebrostudio.movied.utils.ImageLoader
import com.zebrostudio.movied.utils.showAnimation

class MovieListAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<ViewHolder>() {

    private var moviesList: List<MovieItemModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_tile, parent, false),
            parent.context,
            imageLoader
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        moviesList[position].let {
            holder.showImage(it.posterUrl)
            holder.showMovieTitle(it.title)
            holder.attachClickListener()
        }
    }

    fun setList(list: List<MovieItemModel>) {
        moviesList = list
        notifyDataSetChanged()
    }

}

class ViewHolder(
    itemView: View,
    private val context: Context,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemView) {

    fun showImage(url: String) {
        imageLoader.loadImage(context, itemView.findViewById(R.id.moviePoster), url)
    }

    fun showMovieTitle(title: String) {
        itemView.findViewById<TextView>(R.id.movieTitle).text = title
    }

    fun attachClickListener() {
        itemView.findViewById<CardView>(R.id.movieCard).setOnClickListener {
            itemView.findViewById<ImageView>(R.id.moviePoster).showAnimation(R.anim.shrink_fade_out)
        }
    }

}