package com.zebrostudio.movied.screens.fragments

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.movied.R
import com.zebrostudio.movied.circularrecyclerview.CircularHorizontalBTTMode
import com.zebrostudio.movied.circularrecyclerview.RotateXScaleYViewMode
import com.zebrostudio.movied.screens.adapters.MovieBannerListAdapter
import com.zebrostudio.movied.screens.adapters.MovieListAdapter
import com.zebrostudio.movied.utils.ImageLoader
import com.zebrostudio.movied.utils.SnapHelper
import com.zebrostudio.movied.utils.getOrientation
import com.zebrostudio.movied.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_showcase.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieShowcaseFragment : Fragment() {

    private val movieViewModel: MovieViewModel by sharedViewModel()
    private val imageLoader: ImageLoader by inject()
    private var movieTilesDivider: Drawable? = null
    private var movieAdapter: MovieListAdapter? = null
    private var bannerAdapter: MovieBannerListAdapter? = null
    private lateinit var movieSnapHelper: SnapHelper
    private lateinit var bannerSnapHelper: SnapHelper


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
        movieAdapter = MovieListAdapter(imageLoader)
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

}
