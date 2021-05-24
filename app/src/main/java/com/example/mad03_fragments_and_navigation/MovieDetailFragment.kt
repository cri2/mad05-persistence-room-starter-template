package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentMovieDetailBinding
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.models.MovieStore
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModel
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var databaseViewModel: MovieFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)

        databaseViewModel = MovieFavoritesViewModelFactory(MainActivity.app)
            .create(MovieFavoritesViewModel::class.java)

        val args =
            MovieDetailFragmentArgs.fromBundle(requireArguments())   // get navigation arguments

        when (val movieEntry = MovieStore().findMovieByUUID(args.movieId)) {
            null -> {
                Toast.makeText(requireContext(), "Could not load movie data", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigateUp()
            }
            else -> binding.movie = movieEntry
        }

        binding.addToFavorites.setOnClickListener {
            val movie: Movie? = binding.movie

            if (movie != null) {
                databaseViewModel.addMovie(movie)
            }

            Toast.makeText(
                requireContext(),
                "Movie saved to favorites.",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}