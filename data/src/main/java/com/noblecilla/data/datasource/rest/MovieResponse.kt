package com.noblecilla.data.datasource.rest

import com.google.gson.annotations.SerializedName
import com.noblecilla.data.model.MovieRest

data class MovieResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieRest>
)
