package com.example.movieapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
abstract class FavoriteDao {
    @Insert
    abstract fun addData(favoriteList: FavoriteList?)

    @Query("select * from FavoriteList")
    abstract fun getFavoriteData(): List<FavoriteList?>?

    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteList WHERE MovieId=:id)")
    abstract fun isFavorite(id: Int): Boolean

    @Query("DELETE FROM FavoriteList WHERE MovieId=:id")
    abstract fun delete(id: Int)
}