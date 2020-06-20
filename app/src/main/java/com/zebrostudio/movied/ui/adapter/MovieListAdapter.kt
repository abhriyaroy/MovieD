package com.zebrostudio.movied.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movied.R
import com.zebrostudio.movied.data.entity.MovieEntity
import com.zebrostudio.movied.ui.fragment.moviedetail.MovieDetailArgumentSet
import com.zebrostudio.movied.ui.fragment.moviedetail.MovieItemClickCallback
import com.zebrostudio.movied.util.ImageLoader
import kotlinx.android.synthetic.main.item_movie_tile.view.*

class MovieListAdapter(
    private val imageLoader: ImageLoader,
    private val handlerClickCallback: MovieItemClickCallback
) :
    RecyclerView.Adapter<ViewHolder>() {

    private var moviesList: List<MovieEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_tile, parent, false),
            parent.context,
            imageLoader,
            handlerClickCallback
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
            var previousUrl = ""
            if (position != 0) {
                previousUrl = moviesList[position - 1].posterUrl
            }
            var nextUrl = ""
            if (position != moviesList.size - 1) {
                nextUrl = moviesList[position + 1].posterUrl
            }
            holder.showImage(it.posterUrl)
            holder.showMovieTitle(it.title)
            holder.attachClickListener(MovieDetailArgumentSet(previousUrl, nextUrl, it))
        }
    }

    fun setList(list: List<MovieEntity>) {
        moviesList = list
        notifyDataSetChanged()
    }

}

class ViewHolder(
    itemView: View,
    private val context: Context,
    private val imageLoader: ImageLoader,
    private val movieItemViewClickCallback: MovieItemClickCallback
) : RecyclerView.ViewHolder(itemView) {

    fun showImage(url: String) {
        imageLoader.loadImage(context, itemView.moviePoster, url)
    }

    fun showMovieTitle(title: String) {
        itemView.movieTitle.text = title
    }

    fun attachClickListener(movieDetailArgumentSet: MovieDetailArgumentSet) {
        itemView.movieCard.setOnClickListener {
            it.transitionName = movieDetailArgumentSet.selectedMovieItem.posterUrl
            movieItemViewClickCallback.handleClick(it, movieDetailArgumentSet)
        }
    }

}