package com.zebrostudio.movied.screens.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.movied.R
import com.zebrostudio.movied.circularrecyclerview.CircularHorizontalBTTMode
import com.zebrostudio.movied.circularrecyclerview.RotateXScaleYViewMode
import com.zebrostudio.movied.repositories.models.MovieItemModel
import com.zebrostudio.movied.screens.adapters.MovieBannerListAdapter
import com.zebrostudio.movied.screens.adapters.MovieListAdapter
import com.zebrostudio.movied.utils.ImageLoader
import com.zebrostudio.movied.utils.Serializer
import com.zebrostudio.movied.utils.SnapHelper
import com.zebrostudio.movied.utils.getOrientation
import com.zebrostudio.movied.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import kotlinx.android.synthetic.main.fragment_movie_showcase.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieShowcaseFragment : Fragment(), HandleMovieItemClickView {

    private val movieViewModel: MovieViewModel by sharedViewModel()
    private val imageLoader: ImageLoader by inject()
    private val serializer: Serializer by inject()
    private var movieAdapter: MovieListAdapter? = null
    private var bannerAdapter: MovieBannerListAdapter? = null
    private lateinit var movieSnapHelper: SnapHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_showcase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupBannerRecyclerView()
        movieAdapter!!.setList(movieViewModel.moviesLiveData.value!!.moviesList)
        bannerAdapter!!.setList(movieViewModel.moviesLiveData.value!!.moviesList)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager =
            if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
                recyclerView.setViewMode(RotateXScaleYViewMode())
                LinearLayoutManager(context, VERTICAL, false)
            } else {
                recyclerView.setViewMode(CircularHorizontalBTTMode(0f, true))
                LinearLayoutManager(context, HORIZONTAL, false)
            }
        movieAdapter = MovieListAdapter(imageLoader, this)
        recyclerView.adapter = movieAdapter
        recyclerView.setHasFixedSize(true)
        movieSnapHelper = SnapHelper()
        movieSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
                    bannerRecyclerView.scrollBy(0, -dy)
                } else {
                    bannerRecyclerView.scrollBy(-dx, 0)
                }
            }
        })

    }

    private fun setupBannerRecyclerView() {
        bannerRecyclerView.layoutManager =
            if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
                LinearLayoutManager(requireActivity(), VERTICAL, true)
            } else {
                LinearLayoutManager(requireActivity(), HORIZONTAL, true)
            }

        bannerAdapter = MovieBannerListAdapter(imageLoader)
        bannerRecyclerView.adapter = bannerAdapter
        bannerRecyclerView.setHasFixedSize(true)
        bannerRecyclerView.addOnItemTouchListener(object : SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(
                rv: RecyclerView,
                e: MotionEvent
            ): Boolean {
                return true
            }
        })
    }

    override fun handleClick(
        view: View,
        previousMovieUrl: String,
        nextMovieUrl: String,
        movieData: MovieItemModel
    ) {
        requireView().movieTitle.transitionName = movieData.originalName
        val extras = FragmentNavigatorExtras(
            view to movieData.posterUrl,
            requireView().movieTitle to movieData.originalName
        )
        val action =
            MovieShowcaseFragmentDirections.actionMovieShowcaseFragmentToMovieDetails(
                previousMovieUrl = previousMovieUrl,
                nesxtMovieUrl = nextMovieUrl,
                movieData = serializer.getStringFromObj(movieData)
            )

        requireView().findNavController().navigate(action, extras)
    }

}

interface HandleMovieItemClickView {
    fun handleClick(
        view: View,
        previousMovieUrl: String,
        nextMovieUrl: String,
        movieData: MovieItemModel
    )
}
