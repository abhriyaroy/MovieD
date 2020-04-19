package com.zebrostudio.movied.screens.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movied.R
import com.zebrostudio.movied.utils.ImageLoader
import com.zebrostudio.movied.utils.showAnimation
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import org.koin.android.ext.android.inject


class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val imageLoader: ImageLoader by inject()
    var transitionName = "";
    var currentMovieUrl = "";
    var nextMovieUrl = "";
    var previousMovieUrl = "";

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
        setupTransition()
        loadPosters()
        animatePosters()
    }

    private fun setupTransition() {
        transitionName = args.transitionName
        requireView().movieCard.transitionName = transitionName
    }

    private fun loadPosters() {
        currentMovieUrl = args.movieUrl
        previousMovieUrl = args.previousMovieUrl
        nextMovieUrl = args.nesxtMovieUrl
        with(requireView()) {
            imageLoader.loadImage(
                requireContext(),
                this.mainMoviePosterCard.mainMoviePoster,
                currentMovieUrl
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
        with(requireView()) {
            this.mainMoviePosterCard.showAnimation(R.anim.slide_up)
            this.previousMoviePosterCard.showAnimation(R.anim.slide_up, delay = 50)
            this.successorMoviePosterCard.showAnimation(R.anim.slide_up, delay = 50)
        }
    }

}
