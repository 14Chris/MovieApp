package com.example.movieapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.models.Movie
import com.example.movieapp.persistence.FavoriteDao
import com.example.movieapp.persistence.FavoriteList
import com.example.movieapp.ui.movieDetail.MovieDetailViewModel
import kotlinx.coroutines.*

class FavoritesViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {
    private var _movies = MutableLiveData<ArrayList<FavoriteList>>()

    val movies: LiveData<ArrayList<FavoriteList>>
        get() = _movies

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init{
        uiScope.launch {
            getFavoritesMovies()
        }
    }

    suspend fun getFavoritesMovies(){
        withContext(Dispatchers.IO) {
            _movies.postValue(favoriteDao.getFavoriteData() as ArrayList<FavoriteList>?)
        }
    }
}

class FavoritesViewModelFactory(private val favoriteDao: FavoriteDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(favoriteDao) as T
    }
}
