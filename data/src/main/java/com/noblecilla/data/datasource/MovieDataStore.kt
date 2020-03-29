package com.noblecilla.data.datasource

import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.Result

interface MovieDataStore {
    suspend fun nowPlayingRest(page: Int): Result<List<MovieDomain>>? = null
    suspend fun popularRest(page: Int): Result<List<MovieDomain>>? = null
    suspend fun topRatedRest(page: Int): Result<List<MovieDomain>>? = null
    suspend fun upcomingRest(page: Int): Result<List<MovieDomain>>? = null
    suspend fun favoritesDb(): Result<List<MovieDomain>>? = null
    suspend fun favoriteDb(movie: MovieDomain): Result<Boolean>? = null
    suspend fun insertFavoriteDb(movie: MovieDomain): Result<Boolean>? = null
    suspend fun deleteFavoriteDb(movie: MovieDomain): Result<Boolean>? = null
}
