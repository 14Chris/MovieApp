package com.example.movieapp.ui.movieDetail

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.api.MovieApi
import com.example.movieapp.models.MovieDetail
import com.example.movieapp.persistence.FavoriteDao
import com.example.movieapp.persistence.FavoriteList
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailViewModel(val favoriteDao: FavoriteDao, private val movieId:Int) : ViewModel() {
    var _response= MutableLiveData<String>()

    private var _movie = MutableLiveData<MovieDetail>()

    val movie: LiveData<MovieDetail>
        get() = _movie

    private var _movieFav = MutableLiveData<Boolean>()

    val movieFav: LiveData<Boolean>
        get() = _movieFav

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init{
        Log.d("MovieId", movieId.toString())
        _movieFav.value = false
        GetMovieDetail()
    }


    fun favoriteButtonClicked(){
        uiScope.launch {
            SaveOrRemoveFavMovie()
        }
    }

    suspend fun SaveOrRemoveFavMovie(){
        withContext(Dispatchers.IO) {
            //Get fav value for movie
            var fav:Boolean = movieFav.value!!

            //If movie is fav
            if (fav) {
                favoriteDao.delete(movie.value!!.id)
            }
            //If movie isn't fav
            else {
                favoriteDao.addData(FavoriteList(movie.value!!.id, movie.value!!.title, movie.value!!.release_date, movie.value!!.overview, movie.value!!.vote_average, movie.value!!.poster_path))
            }
            _movieFav.postValue(!fav)
        }
    }

    fun GetMovieDetail(){
        MovieApi.retrofitService.GetMovieDetail(movieId).enqueue(object: Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                _response.value = "Erreur : " + t.message
                Log.i("API Movies error", t.message)
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                _movie.value = response.body()!!

                uiScope.launch {
                    isFavorite()
                }
            }
        })
    }

    suspend fun isFavorite() {
        withContext(Dispatchers.IO) {
            val movie = movie.value
            var newVal = false

            if (movie != null) {
                newVal = favoriteDao.isFavorite(movie.id)
            }
            _movieFav.postValue(newVal)

            Log.d("Movie fav", movieFav.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

class MovieDetailViewModelFactory(private val favoriteDao: FavoriteDao, private val movieId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(favoriteDao, movieId) as T
    }
}
