package com.noblecilla.movies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val posterPath: String?,
    val idRest: Int?,
    val backdropPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?
) : Parcelable
