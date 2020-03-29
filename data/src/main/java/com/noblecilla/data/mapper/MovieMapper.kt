package com.noblecilla.data.mapper

import com.noblecilla.data.model.MovieDb
import com.noblecilla.data.model.MovieRest
import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.DataMapper

object MovieMapper : DataMapper<MovieDb, MovieRest, MovieDomain> {

    override fun mapToDbEntity(type: MovieDomain): MovieDb {
        return MovieDb(
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

    override fun mapFromDbEntity(type: MovieDb): MovieDomain {
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

    override fun mapFromDbListEntity(type: List<MovieDb>): List<MovieDomain> {
        return type.map {
            MovieDomain(
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

    override fun mapFromRestListEntity(type: List<MovieRest>): List<MovieDomain> {
        return type.map {
            MovieDomain(
                null,
                it.posterPath,
                it.id,
                it.backdropPath,
                it.title,
                it.voteAverage,
                it.overview,
                it.releaseDate
            )
        }
    }
}
