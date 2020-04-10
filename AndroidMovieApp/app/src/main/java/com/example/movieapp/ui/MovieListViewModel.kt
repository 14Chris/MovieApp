package com.example.movieapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.api.MovieApi
import com.example.movieapp.api.MovieApiService
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel : ViewModel() {
    var _response= MutableLiveData<String>()

    private var _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        _response.value = "Star wars"
        Log.i("MovieListViewModel", "MovieListViewModel created!")
        Log.i("MovieListViewModel", _response.value.toString())
        GetPopularMovies()
    }

    private fun GetPopularMovies(){
        Log.i("GetPopularMovies", "Get popular movies from API!")

//        MovieAppNetwork.movieApi.getPopularMovies().enqueue(object: Callback<List<Movie>>{
//            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                _response.value = "Erreur : " + t.message
//            }
//
//            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                _response.value = "Success"
//                _movies.value = response.body()
//
//                _response.value = _movies.value?.get(0)?.title
//            }
//
//        })

        MovieApi.retrofitService.getPopularMovies().enqueue(object: Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var responseMovies: MovieResponse = response.body()!!
                _response.value ="Success : " + responseMovies.results.size.toString() + " elements"
                _movies.value = responseMovies.results

                _response.value = _movies.value?.get(0)?.title
            }

        })

    }
}