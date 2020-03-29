package com.noblecilla.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieDb(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val posterPath: String?,
    val idRest: Int?,
    val backdropPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?
)
