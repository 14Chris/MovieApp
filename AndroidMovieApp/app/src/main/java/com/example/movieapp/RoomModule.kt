package com.example.movieapp

import android.app.Application
import androidx.room.Room
import com.example.movieapp.persistence.AppDatabase
import com.example.movieapp.persistence.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(mApplication: Application?) {
    private val demoDatabase: AppDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return demoDatabase
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: AppDatabase): FavoriteDao {
        return demoDatabase.getFavoriteDao()
    }

    init {
        demoDatabase =
            Room.databaseBuilder(mApplication!!, AppDatabase::class.java, "demo-db").build()
    }
}