package com.example.movieapp.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.databinding.MovieDetailFragmentBinding


class MovieDetail() : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = MovieDetailArgs.fromBundle(arguments!!)

        // Inflate view and obtain an instance of the binding class
        val binding = MovieDetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(
            this,
            MovieDetailViewModelFactory(args.movieId)
        ).get(
            MovieDetailViewModel::class.java
        )

        binding.viewModel = viewModel

        return binding.root
    }


}
