package com.example.movieapp.ui.movieList.news

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.NewMoviesFragmentBinding
import com.example.movieapp.databinding.PopularMoviesFragmentBinding
import com.example.movieapp.ui.movieList.VerticalMovieAdapter
import com.example.movieapp.ui.movieList.popular.PopularMoviesDirections
import com.example.movieapp.ui.movieList.popular.PopularMoviesViewModel

class NewMovies : Fragment() {

    private lateinit var viewModel: NewMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        val binding = NewMoviesFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(NewMoviesViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = VerticalMovieAdapter()
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

        binding.movieList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    LoadMoreMovies()
                }
            }
        })

        return binding.root
    }

    fun ShowMovieDetail(id: Int){
        val action =
            NewMoviesDirections.actionNewMoviesToMovieDetail(
                id
            )
        this.findNavController().navigate(action)
    }

    fun LoadMoreMovies(){
        Log.i("Load movies", "New movies loading...")
        viewModel.GetMoreNewMovies()
    }
}
