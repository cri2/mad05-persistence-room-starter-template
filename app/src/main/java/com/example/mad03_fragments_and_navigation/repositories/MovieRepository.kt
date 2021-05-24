package com.example.mad03_fragments_and_navigation.repositories

import androidx.lifecycle.LiveData
import com.example.mad03_fragments_and_navigation.database.MovieDao
import com.example.mad03_fragments_and_navigation.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    val getAll: LiveData<List<Movie>> = movieDao.getAll()

    fun addMovie(movie: Movie){
        movieDao.createMovie(movie)
    }

    fun updateMovie(movie: Movie){
        movieDao.updateMovie(movie)
    }

    fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }

    fun clearAll(){
        movieDao.clearTable()
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}