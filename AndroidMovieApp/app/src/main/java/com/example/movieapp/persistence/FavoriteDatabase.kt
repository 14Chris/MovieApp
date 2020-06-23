package com.example.movieapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FavoriteList::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao?
}