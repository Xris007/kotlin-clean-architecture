package com.noblecilla.data.repository

import com.noblecilla.data.datasource.MovieDataStoreFactory
import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.repository.MovieRepository

class MovieDataRepository(private val dataStore: MovieDataStoreFactory) : MovieRepository {

    override suspend fun nowPlayingRest(page: Int) =
        dataStore.create(MovieDataStoreFactory.REST)?.nowPlayingRest(page)!!

    override suspend fun popularRest(page: Int) =
        dataStore.create(MovieDataStoreFactory.REST)?.popularRest(page)!!

    override suspend fun topRatedRest(page: Int) =
        dataStore.create(MovieDataStoreFactory.REST)?.topRatedRest(page)!!

    override suspend fun upcomingRest(page: Int) =
        dataStore.create(MovieDataStoreFactory.REST)?.upcomingRest(page)!!

    override suspend fun favoritesDb() =
        dataStore.create(MovieDataStoreFactory.DB)?.favoritesDb()!!

    override suspend fun favoriteDb(movie: MovieDomain) =
        dataStore.create(MovieDataStoreFactory.DB)?.favoriteDb(movie)!!

    override suspend fun insertFavoriteDb(movie: MovieDomain) =
        dataStore.create(MovieDataStoreFactory.DB)?.insertFavoriteDb(movie)!!

    override suspend fun deleteFavoriteDb(movie: MovieDomain) =
        dataStore.create(MovieDataStoreFactory.DB)?.deleteFavoriteDb(movie)!!
}
