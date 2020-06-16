package com.zebrostudio.movied.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movied.R
import com.zebrostudio.movied.data.entity.MovieEntity
import com.zebrostudio.movied.util.ImageLoader
import com.zebrostudio.movied.util.Serializer
import com.zebrostudio.movied.util.getOrientation
import com.zebrostudio.movied.util.showAnimation
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import org.koin.android.ext.android.inject


class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val imageLoader: ImageLoader by inject()
    private val serializer: Serializer by inject()
    private var nextMovieUrl = ""
    private var previousMovieUrl = ""
    private lateinit var currentMovie: MovieEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureSharedElementTransition()
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        currentMovie = serializer.getObjFromString(args.movieData, MovieEntity::class.java)
        setupTransition()
        loadPosters()
        animatePosters()
        decorateDetails()
        animateDetails()
    }

    private fun configureSharedElementTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun setupTransition() {
        with(requireView()) {
            this.movieCard.transitionName = currentMovie.posterUrl
            this.movieTitle.transitionName = currentMovie.originalName
        }
    }

    private fun loadPosters() {
        previousMovieUrl = args.previousMovieUrl
        nextMovieUrl = args.nextMovieUrl
        loadPosterImage()
        loadPreviousPosterImage()
        loadNextPosterImage()
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
            this.movieTitle.text = currentMovie.originalName
            this.movieReleaseDate.text = currentMovie.releaseDate
            this.movieDescription.text = currentMovie.summary
            this.movieRating.rating = (currentMovie.averageVote / 2).toFloat()
        }
    }

    private fun animateDetails() {
        with(requireView()) {
            this.movieReleaseDate.showAnimation(R.anim.slide_up_fade_in)
            this.movieDescription.showAnimation(R.anim.slide_up_fade_in)
            this.movieRating.showAnimation(R.anim.slide_up_fade_in)
        }
    }

    private fun loadPosterImage() {
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.mainMoviePosterCard.mainMoviePoster,
                currentMovie.posterUrl
            )
        }
    }

    private fun loadPreviousPosterImage() {
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.previousMoviePosterCard.previousMoviePoster,
                previousMovieUrl
            )
        }
    }

    private fun loadNextPosterImage() {
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.successorMoviePosterCard.successorMoviePoster,
                nextMovieUrl
            )
        }
    }

}
