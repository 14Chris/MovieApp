package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Movie

class HorizontalMovieAdapter: RecyclerView.Adapter<HorizontalMovieAdapter.ViewHolder>() {

    private val CONTENT = 0
    private val MORE_BUTTON = 1

    var onItemClick: ((Movie) -> Unit)? = null
    var onMoreButtonClick: (() -> Unit)? = null

    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {

        return if (position < getItemCount() - 1) {
            CONTENT;
        } else {
            MORE_BUTTON;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieAdapter.ViewHolder {
        if(viewType == CONTENT) {

            val layoutInflater =
                LayoutInflater.from(parent.context)

            val view = layoutInflater
                .inflate(
                    R.layout.movie_item_horizontal,
                    parent, false
                )
            return MovieViewHolder(view)
        }
        else {
            val layoutInflater =
                LayoutInflater.from(parent.context)

            val view = layoutInflater
                .inflate(
                    R.layout.more_button_horizontal,
                    parent, false
                )
            return ButtonViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun onBindViewHolder(holder: HorizontalMovieAdapter.ViewHolder, position: Int) {
        if(holder is MovieViewHolder){
            if(position < getItemCount() - 1){
                holder.movieTitle.text = data[position].title

                val imgSource = data[position].poster_path

                Glide.with(holder.movieImage.context)
                    .load("https://image.tmdb.org/t/p/w154" + imgSource)
                    .into(holder.movieImage)
            }
        }
    }

    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    inner class ButtonViewHolder(view: View) : ViewHolder(view) {
        private val moreButton: Button = itemView.findViewById(R.id.more_new_movies)

        init{
            moreButton.setOnClickListener {
                onMoreButtonClick?.invoke()
            }
        }
    }

    inner class MovieViewHolder(view: View) : ViewHolder(view) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieImage: ImageView = itemView.findViewById(R.id.movie_poster)

        init{
            view.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }
}