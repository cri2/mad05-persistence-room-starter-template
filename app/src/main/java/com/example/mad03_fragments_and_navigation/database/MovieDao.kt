
package com.example.mad03_fragments_and_navigation.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mad03_fragments_and_navigation.models.Movie

@Dao
interface MovieDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addMovie(part: Movie)

        @Update
        suspend fun updateMovie(part: Movie)

        @Delete
        suspend fun deleteMovie(part: Movie)

        @Query("DELETE FROM movie_table")
        suspend fun clearTable()

        @Query("SELECT * FROM movie_table ORDER BY id ASC")
        fun getAll(): LiveData<List<Movie>>
}