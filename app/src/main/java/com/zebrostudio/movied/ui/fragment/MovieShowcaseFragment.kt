package com.zebrostudio.movied.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.movied.R
import com.google.android.material.snackbar.Snackbar
import com.zebrostudio.movied.data.entity.MovieEntity
import com.zebrostudio.movied.ui.adapter.MovieBannerListAdapter
import com.zebrostudio.movied.ui.adapter.MovieListAdapter
import com.zebrostudio.movied.ui.fragment.moviedetail.MovieDetailArgumentSet
import com.zebrostudio.movied.ui.fragment.moviedetail.MovieItemClickCallback
import com.zebrostudio.movied.util.ImageLoader
import com.zebrostudio.movied.util.Serializer
import com.zebrostudio.movied.util.SnapHelper
import com.zebrostudio.movied.util.getOrientation
import com.zebrostudio.movied.viewmodel.MovieViewModel
import com.zebrostudio.movied.viewmodel.Status
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import kotlinx.android.synthetic.main.fragment_movie_showcase.*
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import studio.zebro.circularrecyclerview.CircularHorizontalBTTMode
import studio.zebro.circularrecyclerview.RotateXScaleYViewMode

class MovieShowcaseFragment : Fragment(), MovieItemClickCallback {

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
        setupForegroundRecyclerView()
        setupBackgroundRecyclerView()
        observeMovieData()
    }

    override fun handleClick(
        view: View,
        movieDetailArgumentSet: MovieDetailArgumentSet
    ) {
        requireView().movieTitle.transitionName =
            movieDetailArgumentSet.selectedMovieItem.originalName
        requireView().findNavController().navigate(
            getNavigationDirection(movieDetailArgumentSet),
            getNavigationExtras(view, movieDetailArgumentSet.selectedMovieItem)
        )
    }

    private fun setupForegroundRecyclerView() {
        foregroundRecyclerView.layoutManager = getLayoutManagerForForegroundRecyclerView()
        setForegroundRecyclerViewMode()
        movieAdapter = MovieListAdapter(imageLoader, this)
        foregroundRecyclerView.adapter = movieAdapter
        foregroundRecyclerView.setHasFixedSize(true)
        movieSnapHelper = SnapHelper()
        movieSnapHelper.attachToRecyclerView(foregroundRecyclerView)
    }

    private fun getLayoutManagerForForegroundRecyclerView() =
        if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayoutManager(context, VERTICAL, false)
        } else {
            LinearLayoutManager(context, HORIZONTAL, false)
        }

    private fun setForegroundRecyclerViewMode() {
        if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            foregroundRecyclerView.setViewMode(RotateXScaleYViewMode())
        } else {
            foregroundRecyclerView.setViewMode(CircularHorizontalBTTMode(0f, true))
        }
    }

    private fun setupBackgroundRecyclerView() {
        backgroundRecyclerView.layoutManager = getLayoutManagerForBackgroundRecyclerView()
        bannerAdapter = MovieBannerListAdapter(imageLoader)
        backgroundRecyclerView.adapter = bannerAdapter
        backgroundRecyclerView.setHasFixedSize(true)
        addBackgroundRecyclerViewTouchListener()
        scrollBackgroundRecyclerViewOnForegroundRecyclerViewScroll()
    }

    private fun getLayoutManagerForBackgroundRecyclerView() =
        if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayoutManager(requireActivity(), VERTICAL, true)
        } else {
            LinearLayoutManager(requireActivity(), HORIZONTAL, true)
        }

    private fun addBackgroundRecyclerViewTouchListener() {
        backgroundRecyclerView.addOnItemTouchListener(object : SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(
                rv: RecyclerView,
                e: MotionEvent
            ): Boolean {
                return true
            }
        })
    }

    private fun scrollBackgroundRecyclerViewOnForegroundRecyclerViewScroll() {
        foregroundRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (requireContext().getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
                    backgroundRecyclerView.scrollBy(0, -dy)
                } else {
                    backgroundRecyclerView.scrollBy(-dx, 0)
                }
            }
        })
    }

    private fun observeMovieData() {
        movieViewModel.moviesResultData.observe(viewLifecycleOwner, Observer { movies ->
            when (movies.status) {
                Status.LOADING -> showLoadingMessage()
                Status.SUCCESS -> showMoviesList(movies.data!!.moviesList)
                Status.ERROR -> showErrorMessageWithRetryOption()
            }
        })
    }

    private fun showLoadingMessage() {
        Snackbar.make(coordinatorSplash, R.string.loading_movies, Snackbar.LENGTH_INDEFINITE)
            .show()
    }

    private fun showMoviesList(list: List<MovieEntity>) {
        movieAdapter!!.setList(list)
        bannerAdapter!!.setList(list)
    }

    private fun showErrorMessageWithRetryOption() {
        Snackbar.make(coordinatorSplash, R.string.failed_to_load_movies, Snackbar.LENGTH_INDEFINITE)
            .apply {
                setAction(R.string.retry) { movieViewModel.getPopularMovies() }
                show()
            }
    }

    private fun getNavigationExtras(view: View, movieData: MovieEntity) = FragmentNavigatorExtras(
        view to movieData.posterUrl,
        requireView().movieTitle to movieData.originalName
    )

    private fun getNavigationDirection(
        movieDetailArgumentSet: MovieDetailArgumentSet
    ) = MovieShowcaseFragmentDirections.actionMovieShowcaseFragmentToMovieDetails(
        serializer.getStringFromObj(movieDetailArgumentSet)
    )

}