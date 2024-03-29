package com.example.movieapp.api

import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieDetail
import com.example.movieapp.models.MovieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException


const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNzVjNjFlOTlkZmUwYjg4NWZkMDViNjY5NzY5ZTU3NCIsInN1YiI6IjVkOGE1ZjhmYWJmOGUyMDAxOTc2ZDFiMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t52olZ-TumtKW4ofU6wpkRvKDDUAW6d56101hJfEUqQ"

var client = OkHttpClient.Builder().addInterceptor(object : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer "+ API_BEARER_TOKEN)
            .build()
        return chain.proceed(newRequest)
    }
}).build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface MovieApiService {
    @GET("movie/popular?language=fr-FR&region=FR")
    fun GetPopularMovies(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/upcoming?language=fr-FR&region=FR")
    fun GetUpcomingMovies(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/now_playing?language=fr-FR&region=FR")
    fun GetNewMovies(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{movieId}?language=fr-FR")
    fun GetMovieDetail(@Path("movieId") movieId:Int): Call<MovieDetail>

    @GET("search/movie?language=fr-FR")
    fun SearchMovie(@Query("query") search:String, @Query("page") page: Int): Call<MovieResponse>

}

object MovieApi {
    val retrofitService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java) }
}

