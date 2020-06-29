package com.example.movieapp.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.databinding.MovieDetailFragmentBinding
import com.example.movieapp.persistence.AppDatabase



class MovieDetail(): Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = MovieDetailArgs.fromBundle(arguments!!)

        // Inflate view and obtain an instance of the binding class
        val binding = MovieDetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application

        var favoriteDao = AppDatabase.getInstance(application).favoriteDao()



        viewModel = ViewModelProviders.of(
            this,
            MovieDetailViewModelFactory(favoriteDao, args.movieId)
        ).get(
            MovieDetailViewModel::class.java
        )

        binding.viewModel = viewModel

//        binding.movieFav.setOnClickListener(View.OnClickListener {
//            viewModel.run { FavButtonClicked() }
//            var favButton = binding.movieFav
//
//            if(viewModel.movieFav){
//                favButton.setImageResource(R.drawable.ic_fav);
//            }
//            else{
//                favButton.setImageResource(R.drawable.ic_fav_border);
//            }
//        })
        return binding.root
    }

}
