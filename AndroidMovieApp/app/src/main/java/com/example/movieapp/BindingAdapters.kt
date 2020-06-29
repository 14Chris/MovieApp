package com.example.movieapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("posterUrl")
fun bindPosterImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load("https://image.tmdb.org/t/p/original" + imgUrl)
            .into(imgView)
    }
}

@BindingAdapter("backgroundUrl")
fun bindBackgroundImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load("https://image.tmdb.org/t/p/original" + imgUrl)
            .into(imgView)
    }
}

//@BindingAdapter("android:src")
//fun setImageViewResource(imageView: ImageView, resource: Int) {
//    imageView.setImageResource(resource)
//}