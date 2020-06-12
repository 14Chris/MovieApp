package com.example.movieapp.ui.search

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

class SearchViewModel : ViewModel() {
    var _response= MutableLiveData<String>()

    private var _movies = MutableLiveData<ArrayList<Movie>>()

    val movies: LiveData<ArrayList<Movie>>
        get() = _movies

    private var _currentPage = MutableLiveData<Int>()

    val currentPage: LiveData<Int>
        get() = _currentPage

    private var _searchTerm = MutableLiveData<String>()

    val searchTerm: LiveData<String>
        get() = _searchTerm

    init {
        _currentPage.value = 1
        _movies.value = ArrayList<Movie>()
    }

    private fun GetSearchMovies(){
        MovieApi.retrofitService.SearchMovie(searchTerm.value!!, 1).enqueue(object:
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.body() != null){
                    var responseMovies: MovieResponse = response.body()!!
                    _currentPage.value = responseMovies.page

                    Log.i("API Movies size",responseMovies.results.size.toString())

                    _movies.value = ArrayList<Movie>()
                    _movies.value?.addAll(responseMovies.results)
                    _movies.notifyObserver()
                }
            }
        })
    }

    fun GetMoreSearchMovies(){
        _currentPage.value = _currentPage.value?.plus(1)
        MovieApi.retrofitService.SearchMovie(searchTerm.value!!, currentPage.value!!).enqueue(object:
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                var responseMovies: MovieResponse = response.body()!!
                _currentPage.value = responseMovies.page

                Log.i("API Movies size",responseMovies.results.size.toString())

                _movies.value?.addAll(responseMovies.results)
                _movies.notifyObserver()

                _response.value = _movies.value?.get(0)?.title
            }
        })
    }


    fun SearchMovies(searchTerm: String){
        if(searchTerm != null && searchTerm.length > 0){
            this._searchTerm.value = searchTerm
            GetSearchMovies()
        }
        else{
            this._movies.value = ArrayList<Movie>()
        }

    }
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}