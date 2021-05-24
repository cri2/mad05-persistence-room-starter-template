package com.example.mad03_fragments_and_navigation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad03_fragments_and_navigation.database.AppDatabase
import com.example.mad03_fragments_and_navigation.models.Movie
import com.example.mad03_fragments_and_navigation.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieFavoritesViewModel (
    app: Application
): ViewModel() {

    val getAll: LiveData<List<Movie>>
    private val repository: MovieRepository

    init {
        val movieDao = AppDatabase.getDatabase(
            app
        ).movieDao()
        repository = MovieRepository(movieDao)
        getAll = repository.getAll
    }

    fun addMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movie)
        }
    }

    fun updateMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMovie(movie)
        }
    }

    fun deleteMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(movie)
        }
    }

    fun clearAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAll()
        }
    }

}