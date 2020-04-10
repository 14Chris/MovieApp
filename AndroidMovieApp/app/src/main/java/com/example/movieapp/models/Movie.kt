package com.example.movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String)


data class MovieResponse(val page: Int, val results: List<Movie>, val total_results: Int, val total_pages: Int)