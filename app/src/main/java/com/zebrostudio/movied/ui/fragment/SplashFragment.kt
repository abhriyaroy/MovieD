package com.zebrostudio.movied.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.movied.R
import com.google.android.material.snackbar.Snackbar
import com.zebrostudio.movied.data.entity.MoviesResultEntity
import com.zebrostudio.movied.exception.NoInternetException
import com.zebrostudio.movied.viewmodel.MovieViewModel
import com.zebrostudio.movied.viewmodel.ResourceResult
import com.zebrostudio.movied.viewmodel.Status
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashFragment : Fragment() {

    private val movieViewModel: MovieViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMovieData(view)
        movieViewModel.getPopularMovies()
    }

    private fun observeMovieData(view: View) {
        movieViewModel.moviesResultData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> lottieSplash.playAnimation()
                Status.SUCCESS -> showMovieShowcaseScreen(view)
                Status.ERROR -> handleErrorState(it)
            }
        })
    }

    private fun showMovieShowcaseScreen(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_splashFragment_to_movieShowcaseFragment)
    }

    private fun handleErrorState(result: ResourceResult<MoviesResultEntity>) {
        if (result.error is NoInternetException) {
            showMovieLoadingErrorMessageWithRetryButton()
        } else {
            showGenericError()
        }
    }

    private fun showMovieLoadingErrorMessageWithRetryButton() {
        Snackbar.make(coordinatorSplash, R.string.failed_to_load_movies, Snackbar.LENGTH_INDEFINITE)
            .apply {
                setAction(R.string.retry) { movieViewModel.getPopularMovies() }
                show()
            }
    }

    private fun showGenericError() {
        Snackbar.make(coordinatorSplash, R.string.something_went_wrong, Snackbar.LENGTH_INDEFINITE)
    }

}
