package com.zebrostudio.movied.screens.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movied.R
import com.zebrostudio.movied.repositories.models.MovieItemModel
import com.zebrostudio.movied.utils.ImageLoader
import com.zebrostudio.movied.utils.Serializer
import com.zebrostudio.movied.utils.getOrientation
import com.zebrostudio.movied.utils.showAnimation
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import org.koin.android.ext.android.inject


class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val imageLoader: ImageLoader by inject()
    private val serializer: Serializer by inject()
    private var nextMovieUrl = ""
    private var previousMovieUrl = ""
    private lateinit var currentMovieItem: MovieItemModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentMovieItem = serializer.getObjFromString(args.movieData, MovieItemModel::class.java)
        setupTransition()
        loadPosters()
        animatePosters()
        decorateDetails()
        animateDetails()
    }

    private fun setupTransition() {
        with(requireView()) {
            this.movieCard.transitionName = currentMovieItem.posterUrl
            this.movieTitle.transitionName = currentMovieItem.originalName
        }
    }

    private fun loadPosters() {
        previousMovieUrl = args.previousMovieUrl
        nextMovieUrl = args.nesxtMovieUrl
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.mainMoviePosterCard.mainMoviePoster,
                currentMovieItem.posterUrl
            )
            imageLoader.loadImage(
                requireContext(),
                this.previousMoviePosterCard.previousMoviePoster,
                previousMovieUrl
            )
            imageLoader.loadImage(
                requireContext(),
                this.successorMoviePosterCard.successorMoviePoster,
                nextMovieUrl
            )
        }
    }

    private fun animatePosters() {
        if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            with(requireView()) {
                this.previousMoviePosterCard.showAnimation(R.anim.slide_left)
                this.mainMoviePosterCard.showAnimation(R.anim.slide_left, delay = 50)
                this.successorMoviePosterCard.showAnimation(R.anim.slide_left, delay = 50)
            }
        } else {
            with(requireView()) {
                this.mainMoviePosterCard.showAnimation(R.anim.slide_up)
                this.previousMoviePosterCard.showAnimation(R.anim.slide_up, delay = 50)
                this.successorMoviePosterCard.showAnimation(R.anim.slide_up, delay = 50)
            }
        }
    }

    private fun decorateDetails() {
        with(requireView()) {
            this.movieTitle.text = currentMovieItem.originalName
            this.movieReleaseDate.text = currentMovieItem.releaseDate
            this.movieDescription.text = currentMovieItem.summary
            this.movieRating.rating = (currentMovieItem.averageVote / 2).toFloat()
        }
    }

    private fun animateDetails() {
        with(requireView()) {
            this.movieReleaseDate.showAnimation(R.anim.slide_up_fade_in)
            this.movieDescription.showAnimation(R.anim.slide_up_fade_in)
            this.movieRating.showAnimation(R.anim.slide_up_fade_in)
        }
    }

}
