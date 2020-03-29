package com.noblecilla.domain.model

data class MovieDomain(
    val id: Int?,
    val posterPath: String?,
    val idRest: Int?,
    val backdropPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?
)
