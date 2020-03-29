package com.noblecilla.data.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.noblecilla.data.model.MovieDb

@Dao
interface MovieDao {

    @Query("select * from movie")
    suspend fun favoritesDb(): List<MovieDb>

    @Query("select * from movie where idRest = :id")
    suspend fun favoriteDb(id: Int): MovieDb

    @Insert(onConflict = REPLACE)
    suspend fun insertFavoriteDb(movie: MovieDb)

    @Query("delete from movie where idRest = :id")
    suspend fun deleteFavoriteDb(id: Int)
}
