package com.noblecilla.data.datasource.db

import android.content.Context
import com.noblecilla.data.datasource.MovieDataStore
import com.noblecilla.data.mapper.MovieMapper
import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.Result

class MovieDbDataStore(context: Context) : MovieDataStore {

    private lateinit var movieDao: MovieDao

    init {
        val db = MovieDatabase.getInstance(context)
        db?.let { movieDao = it.movieDao() }
    }

    override suspend fun favoritesDb(): Result<List<MovieDomain>>? {
        return try {
            Result.Success(MovieMapper.mapFromDbListEntity(movieDao.favoritesDb()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun favoriteDb(movie: MovieDomain): Result<Boolean>? {
        return try {
            MovieMapper.mapFromDbEntity(movieDao.favoriteDb(movie.idRest!!))
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun insertFavoriteDb(movie: MovieDomain): Result<Boolean>? {
        return try {
            movieDao.insertFavoriteDb(MovieMapper.mapToDbEntity(movie))
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteFavoriteDb(movie: MovieDomain): Result<Boolean>? {
        return try {
            movieDao.deleteFavoriteDb(MovieMapper.mapToDbEntity(movie).idRest!!)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
