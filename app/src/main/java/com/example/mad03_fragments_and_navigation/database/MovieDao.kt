package com.example.mad03_fragments_and_navigation.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mad03_fragments_and_navigation.models.Movie

@Dao
interface MovieDao {

    // methods according template

        @Insert
        suspend fun createMovie(part: Movie)

        @Update
        suspend fun updateMovie(part: Movie)

        @Delete
        suspend fun deleteMovie(part: Movie)

        @Query("DELETE FROM movie_data")
        suspend fun clearTable()

        @Query("SELECT * FROM movie_data ORDER BY id ASC")
        fun getAll(): LiveData<List<Movie>>
}