package com.example.movieapp

import android.app.Application
import com.example.movieapp.persistence.AppDatabase
import com.example.movieapp.persistence.FavoriteDao
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity?)
    fun favoriteDao(): FavoriteDao?
    fun appDatabase(): AppDatabase?
    fun application(): Application?
}