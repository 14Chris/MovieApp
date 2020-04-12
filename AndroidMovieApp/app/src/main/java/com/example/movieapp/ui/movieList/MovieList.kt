package com.example.movieapp.ui.movieList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieListFragmentBinding
import com.example.movieapp.ui.movieDetail.MovieDetail

class MovieList : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        val binding = MovieListFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = MovieAdapter()
        binding.movieList.adapter = adapter
        binding.movieList.setLayoutManager(LinearLayoutManager(context));


        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        adapter.onItemClick = { movie ->
            // do something with your item
            Log.d("Movie clicked", movie.id.toString())
            ShowMovieDetail(movie.id)
        }

        return binding.root
    }

    fun ShowMovieDetail(id: Int){
        val action = MovieListDirections.actionMovieListToMovieDetail(id)
        this.findNavController().navigate(action)
    }
}
