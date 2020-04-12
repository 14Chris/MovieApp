package com.example.movieapp.ui.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.api.MovieApi
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
        GetPopularMovies()
    }

    private fun GetPopularMovies(){
        MovieApi.retrofitService.getPopularMovies().enqueue(object: Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var responseMovies: MovieResponse = response.body()!!
                _response.value ="Success : " + responseMovies.results.size.toString() + " elements"
                Log.i("API Movies size",responseMovies.results.size.toString())
                _movies.value = responseMovies.results

                _response.value = _movies.value?.get(0)?.title
            }

        })
    }
}