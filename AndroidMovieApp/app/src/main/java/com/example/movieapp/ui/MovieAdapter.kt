package com.example.movieapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.movieapp.R
import com.example.movieapp.api.MovieApiService
import com.example.movieapp.models.Movie

class MovieAdapter() : RecyclerView.Adapter<ViewHolder>() {

    var data =  listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(
                R.layout.movie_item,
                parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = data[position].title
        holder.movieDate.text = data[position].release_date
        holder.movieNote.text = data[position].vote_average.toString()
        holder.movieImgUrl.text = data[position].poster_path

        val imgSource =  data[position].poster_path

            Glide.with(holder.movieImage.context)
                .load("https://image.tmdb.org/t/p/w154"+imgSource)
                .into(holder.movieImage)
        }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val movieDate: TextView = itemView.findViewById(R.id.movie_date)
    val movieNote: TextView = itemView.findViewById(R.id.movie_note)
    val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
    val movieImgUrl: TextView = itemView.findViewById(R.id.movie_img_url)
}