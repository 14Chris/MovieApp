package com.example.movieapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FavoriteList(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "MovieId")
    val movieId: Int
)