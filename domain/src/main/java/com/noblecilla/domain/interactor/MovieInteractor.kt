package com.noblecilla.domain.interactor

import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.repository.MovieRepository

class MovieInteractor(private val repository: MovieRepository) {
    suspend fun nowPlayingRest(page: Int) = repository.nowPlayingRest(page)
    suspend fun popularRest(page: Int) = repository.popularRest(page)
    suspend fun topRatedRest(page: Int) = repository.topRatedRest(page)
    suspend fun upcomingRest(page: Int) = repository.upcomingRest(page)
    suspend fun favoritesDb() = repository.favoritesDb()
    suspend fun favoriteDb(movie: MovieDomain) = repository.favoriteDb(movie)
    suspend fun insertFavoriteDb(movie: MovieDomain) = repository.insertFavoriteDb(movie)
    suspend fun deleteFavoriteDb(movie: MovieDomain) = repository.deleteFavoriteDb(movie)
}
