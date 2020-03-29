package com.noblecilla.domain.repository

import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.Result

interface MovieRepository {
    suspend fun nowPlayingRest(page: Int): Result<List<MovieDomain>>
    suspend fun popularRest(page: Int): Result<List<MovieDomain>>
    suspend fun topRatedRest(page: Int): Result<List<MovieDomain>>
    suspend fun upcomingRest(page: Int): Result<List<MovieDomain>>
    suspend fun favoritesDb(): Result<List<MovieDomain>>
    suspend fun favoriteDb(movie: MovieDomain): Result<Boolean>
    suspend fun insertFavoriteDb(movie: MovieDomain): Result<Boolean>
    suspend fun deleteFavoriteDb(movie: MovieDomain): Result<Boolean>
}
