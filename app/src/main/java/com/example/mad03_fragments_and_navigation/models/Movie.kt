package com.example.mad03_fragments_and_navigation.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.mad03_fragments_and_navigation.R

@Entity(tableName = "movie_table")
data class Movie(
    var title: String = "",
    var description: String = ""
) {
    @PrimaryKey
    var id: Long? = 0L
    var rating: Float = 0.0F
        set(value) {
            if(value in 0.0..5.0) field = value
            else throw IllegalArgumentException("Rating value must be between 0 and 5")
        }
    var note: String = ""
    var imageId: Int = R.drawable.no_preview_3

    // ignore non basic types
    @Ignore
    var actors: MutableList<String> = mutableListOf()
    @Ignore
    var creators: MutableList<String> = mutableListOf()
    @Ignore
    var genres: List<String>? = null
}
