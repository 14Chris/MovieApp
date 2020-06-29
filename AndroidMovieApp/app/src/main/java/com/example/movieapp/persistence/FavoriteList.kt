package com.example.movieapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class FavoriteList(val movieId: Int,
                   val title: String,
                   val release_date: String,
                   val overview: String,
                   val vote_average: Float,
                   val poster_path: String?){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}