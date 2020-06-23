package com.example.movieapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favoritelist")
class FavoriteList {
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "prname")
    var name: String? = null

}