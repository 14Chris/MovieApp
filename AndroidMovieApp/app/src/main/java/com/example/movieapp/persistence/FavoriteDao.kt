package com.example.movieapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
abstract class FavoriteDao {

    @Insert
    abstract fun addData(favoriteList: FavoriteList?)

    @Query("select * from favoritelist")
    abstract fun getFavoriteData(): List<FavoriteList?>?

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    abstract fun isFavorite(id: Int): Int

    @Delete
    abstract fun delete(favoriteList: FavoriteList?)
}