package com.example.movieapp.ui.favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.movieapp.databinding.FavoritesFragmentBinding
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.persistence.AppDatabase
import com.example.movieapp.ui.movieDetail.MovieDetailViewModel
import com.example.movieapp.ui.movieDetail.MovieDetailViewModelFactory
import com.example.movieapp.ui.movieList.VerticalFavMovieAdapter
import com.example.movieapp.ui.movieList.VerticalMovieAdapter
import com.example.movieapp.ui.search.SearchDirections
import com.example.movieapp.ui.search.SearchViewModel

class Favorites : Fragment() {

    companion object {
        fun newInstance() = Favorites()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        val binding = FavoritesFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application

        var favoriteDao = AppDatabase.getInstance(application).favoriteDao()


        viewModel = ViewModelProviders.of(
            this,
            FavoritesViewModelFactory(favoriteDao)
        ).get(
            FavoritesViewModel::class.java
        )

        binding.viewModel = viewModel

        val adapter = VerticalFavMovieAdapter()
        binding.movieList.adapter = adapter
        binding.movieList.setLayoutManager(LinearLayoutManager(context));


        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        adapter.onItemClick = { movie ->
            ShowMovieDetail(movie.movieId)
        }

        return binding.root
    }


    fun ShowMovieDetail(id: Int){
        val action =
            FavoritesDirections.actionFavoritesToMovieDetail(
                id
            )
        this.findNavController().navigate(action)
    }

}