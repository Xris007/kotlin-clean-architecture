package com.noblecilla.movies.mapper

import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.ViewMapper
import com.noblecilla.movies.model.Movie

object MovieMapper : ViewMapper<MovieDomain, Movie> {

    override fun mapToEntity(type: Movie): MovieDomain {
        return MovieDomain(
            type.id,
            type.posterPath,
            type.idRest,
            type.backdropPath,
            type.title,
            type.voteAverage,
            type.overview,
            type.releaseDate
        )
    }

    override fun mapFromEntity(type: MovieDomain): Movie {
        return Movie(
            type.id,
            type.posterPath,
            type.idRest,
            type.backdropPath,
            type.title,
            type.voteAverage,
            type.overview,
            type.releaseDate
        )
    }

    override fun mapFromListEntity(type: List<MovieDomain>): List<Movie> {
        return type.map {
            Movie(
                it.id,
                it.posterPath,
                it.idRest,
                it.backdropPath,
                it.title,
                it.voteAverage,
                it.overview,
                it.releaseDate
            )
        }
    }
}
