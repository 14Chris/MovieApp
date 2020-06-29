package com.example.movieapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class FavoriteList(var movieId:Int){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}