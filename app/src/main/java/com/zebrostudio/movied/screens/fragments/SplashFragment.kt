package com.zebrostudio.movied.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.movied.R
import com.zebrostudio.movied.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
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
        movieViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(view)
                .navigate(R.id.action_splashFragment_to_movieShowcaseFragment)
        })
        movieViewModel.getPopularMovies()
    }

}
