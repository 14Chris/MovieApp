package com.example.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.HomeFragmentBinding


class Home : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding.viewModel = viewModel

        val newMoviesAdapter = HorizontalMovieAdapter()
        binding.newMoviesList.adapter = newMoviesAdapter
        binding.newMoviesList.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )



        viewModel.newMovies.observe(viewLifecycleOwner, Observer {
            it?.let {
                newMoviesAdapter.data = it
            }
        })

        newMoviesAdapter.onItemClick = { movie ->
            // do something with your item
            Log.d("Movie clicked", movie.id.toString())
            ShowMovieDetail(movie.id)
        }

        val popularMoviesAdapter = HorizontalMovieAdapter()
        binding.popularMoviesList.adapter = popularMoviesAdapter
        binding.popularMoviesList.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )


        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            it?.let {
                popularMoviesAdapter.data = it
            }
        })

        popularMoviesAdapter.onItemClick = { movie ->
            // do something with your item
            Log.d("Movie clicked", movie.id.toString())
            ShowMovieDetail(movie.id)
        }

        val upcomingMoviesAdapter = HorizontalMovieAdapter()
        binding.upcommingMoviesList.adapter = upcomingMoviesAdapter
        binding.upcommingMoviesList.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        );


        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            it?.let {
                upcomingMoviesAdapter.data = it
            }
        })

        upcomingMoviesAdapter.onItemClick = { movie ->
            // do something with your item
            Log.d("Movie clicked", movie.id.toString())
            ShowMovieDetail(movie.id)
        }

        binding.moreNewMovies.setOnClickListener({

        })

        binding.morePopularMovies.setOnClickListener({
            ShowPopularMovies()
        })

        binding.moreUpcomingMovies.setOnClickListener({})

        return binding.root
    }


    fun ShowMovieDetail(id: Int) {
        val action = HomeDirections.actionHomeToMovieDetail(id)
        this.findNavController().navigate(action)
    }

    fun ShowPopularMovies() {
        val action = HomeDirections.actionHomeToPopularMovies()
        this.findNavController().navigate(action)
    }
}
