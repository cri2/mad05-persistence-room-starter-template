package com.example.mad03_fragments_and_navigation

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.mad03_fragments_and_navigation.adapters.FavoritesListAdapter
import com.example.mad03_fragments_and_navigation.databinding.FragmentFavoritesBinding
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModel
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory


class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var databaseViewModel: MovieFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val adapter = FavoritesListAdapter(
            dataSet = listOf(),     // start with empty list
            onDeleteClicked = { movieId -> onDeleteMovieClicked(movieId) },   // pass functions to adapter
            onEditClicked = { movie -> onEditMovieClicked(movie) },           // pass functions to adapter
        )

        databaseViewModel = MovieFavoritesViewModelFactory(MainActivity.app)
            .create(MovieFavoritesViewModel::class.java)
        databaseViewModel.getAll.observe(viewLifecycleOwner, { movie ->
            adapter.updateDataSet(movie)
        })

        with(binding){
            recyclerView.adapter = adapter
        }

        binding.clearBtn.setOnClickListener {
            databaseViewModel.clearAll()
        }

        return binding.root
    }

    // This is called when recyclerview item edit button is clicked
    private fun onEditMovieClicked(movieObj: Movie){

        val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Add Note to Movie")

        // Set up the input
        val input = EditText(this.requireContext())

        // Specify the type of input expected;
        // this, for example, sets the input as a password, and will mask the text
        input.hint = "Enter Note"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { _, _ ->

            // note function
            val mText = input.text.toString()
            movieObj.note = mText
            databaseViewModel.updateMovie(movieObj)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.create().show()
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long){

        val allMovies: List<Movie>? = databaseViewModel.getAll.value
        if (allMovies != null) {
            val matchMovie: Movie = allMovies.first { it.id  == movieId }
            databaseViewModel.deleteMovie(matchMovie)
        }

    }

}