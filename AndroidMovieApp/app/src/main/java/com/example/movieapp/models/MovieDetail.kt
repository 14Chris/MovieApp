package com.example.movieapp.models

data class MovieDetail(val id: Int, val title:String,
                       val release_date: String,
                       val overview:String,
                       val vote_average: Float,
                       val runtime: Int,
                       val backdrop_path: String,
                       val poster_path: String) {
    val vote: String
        get() = vote_average.toString()

    val duration: String
        get() = runtime.toString()
}