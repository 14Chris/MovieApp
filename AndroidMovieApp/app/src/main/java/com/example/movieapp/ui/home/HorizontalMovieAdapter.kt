package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Movie

class HorizontalMovieAdapter: RecyclerView.Adapter<HorizontalMovieAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieAdapter.ViewHolder {
        val layoutInflater =
        LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(
                R.layout.movie_item_horizontal,
                parent, false
            )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HorizontalMovieAdapter.ViewHolder, position: Int) {
        holder.movieTitle.text = data[position].title

        val imgSource = data[position].poster_path

        Glide.with(holder.movieImage.context)
            .load("https://image.tmdb.org/t/p/w154" + imgSource)
            .into(holder.movieImage)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieImage: ImageView = itemView.findViewById(R.id.movie_poster)

        init{
            view.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }

    }
}