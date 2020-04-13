package com.example.movieapp.ui.home

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

class HomeViewModel : ViewModel() {
    var _response= MutableLiveData<String>()

    private var _popularMovies = MutableLiveData<List<Movie>>()

    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies


    private var _newMovies = MutableLiveData<List<Movie>>()

    val newMovies: LiveData<List<Movie>>
        get() = _newMovies

    private var _upcomingMovies = MutableLiveData<List<Movie>>()

    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies


    init{
        GetPopularMovies()
        GetNewMovies()
        GetUpcomingMovies()
    }

    fun GetPopularMovies(){
        MovieApi.retrofitService.GetPopularMovies().enqueue(object: Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var responseMovies: MovieResponse = response.body()!!
                _response.value ="Success : " + responseMovies.results.size.toString() + " elements"
                Log.i("API Movies size",responseMovies.results.size.toString())
                _popularMovies.value = responseMovies.results
            }

        })
    }

    fun GetNewMovies(){
        MovieApi.retrofitService.GetNewMovies().enqueue(object: Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var responseMovies: MovieResponse = response.body()!!
                _response.value ="Success : " + responseMovies.results.size.toString() + " elements"
                Log.i("API Movies size",responseMovies.results.size.toString())
                _newMovies.value = responseMovies.results
            }

        })
    }

    fun GetUpcomingMovies(){
        MovieApi.retrofitService.GetUpcomingMovies().enqueue(object: Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var responseMovies: MovieResponse = response.body()!!
                _response.value ="Success : " + responseMovies.results.size.toString() + " elements"
                Log.i("API Movies size",responseMovies.results.size.toString())
                _upcomingMovies.value = responseMovies.results
            }
        })
    }
}
