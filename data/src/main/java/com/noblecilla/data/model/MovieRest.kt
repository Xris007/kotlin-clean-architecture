package com.noblecilla.data.model

import com.google.gson.annotations.SerializedName

data class MovieRest(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?
)
