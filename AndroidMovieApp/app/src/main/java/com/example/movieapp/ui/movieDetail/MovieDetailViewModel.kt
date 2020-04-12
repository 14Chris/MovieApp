package com.example.movieapp.ui.movieDetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.api.MovieApi
import com.example.movieapp.models.MovieDetail
import com.example.movieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailViewModel(private val movieId:Int) : ViewModel() {
    var _response= MutableLiveData<String>()

    private var _movie = MutableLiveData<MovieDetail>()

    val movie: LiveData<MovieDetail>
        get() = _movie


    init{
        Log.d("MovieId", movieId.toString())
        GetMovieDetail()
    }

    fun GetMovieDetail(){
        MovieApi.retrofitService.GetMovieDetail(movieId).enqueue(object: Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                _movie.value = response.body()!!
            }

        })
    }
}

class MovieDetailViewModelFactory(private val movieId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(movieId) as T
    }

}
