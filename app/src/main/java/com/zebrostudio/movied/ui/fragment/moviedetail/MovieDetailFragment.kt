package com.zebrostudio.movied.ui.fragment.moviedetail

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
import com.zebrostudio.movied.util.ImageLoader
import com.zebrostudio.movied.util.Serializer
import com.zebrostudio.movied.util.getOrientation
import com.zebrostudio.movied.util.showAnimation
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import org.koin.android.ext.android.inject


class MovieDetailFragment : Fragment() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val imageLoader: ImageLoader by inject()
    private val serializer: Serializer by inject()
    private lateinit var movieDetailArgumentSet: MovieDetailArgumentSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureSharedElementTransition()
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureTransition(view)
        getNavArguments()
        setupTransition()
        loadPosters()
        animatePosters()
        decorateDetails()
        animateDetails()
    }

    private fun configureTransition(view: View) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun getNavArguments() {
        movieDetailArgumentSet =
            serializer.getObjFromString(args.movieDetailData, MovieDetailArgumentSet::class.java)
    }

    private fun configureSharedElementTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun setupTransition() {
        with(requireView()) {
            this.movieCard.transitionName = movieDetailArgumentSet.selectedMovieItem.posterUrl
            this.movieTitle.transitionName = movieDetailArgumentSet.selectedMovieItem.originalName
        }
    }

    private fun loadPosters() {
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
            this.movieTitle.text = movieDetailArgumentSet.selectedMovieItem.originalName
            this.movieReleaseDate.text = movieDetailArgumentSet.selectedMovieItem.releaseDate
            this.movieDescription.text = movieDetailArgumentSet.selectedMovieItem.summary
            this.movieRating.rating =
                (movieDetailArgumentSet.selectedMovieItem.averageVote / 2).toFloat()
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
                movieDetailArgumentSet.selectedMovieItem.posterUrl
            )
        }
    }

    private fun loadPreviousPosterImage() {
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.previousMoviePosterCard.previousMoviePoster,
                movieDetailArgumentSet.previousMovieUrl
            )
        }
    }

    private fun loadNextPosterImage() {
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.successorMoviePosterCard.successorMoviePoster,
                movieDetailArgumentSet.nextMovieUrl
            )
        }
    }

}
