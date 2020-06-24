package com.example.movieapp.ui.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Movie


class VerticalMovieAdapter() : RecyclerView.Adapter<VerticalMovieAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    var data = listOf<Movie>()
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
                parent, false
            )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = data[position].title
        holder.movieDate.text = data[position].release_date
        holder.movieNote.text = data[position].vote_average.toString()

        val imgSource = data[position].poster_path

        Glide.with(holder.movieImage.context)
            .load("https://image.tmdb.org/t/p/w154" + imgSource)
            .into(holder.movieImage)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieDate: TextView = itemView.findViewById(R.id.movie_date)
        val movieNote: TextView = itemView.findViewById(R.id.movie_note)
        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)

        init{
            view.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}

