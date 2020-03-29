package com.noblecilla.data.datasource.rest

import com.noblecilla.data.BuildConfig
import com.noblecilla.data.datasource.MovieDataStore
import com.noblecilla.data.mapper.MovieMapper
import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.Result

class MovieRestDataStore : MovieDataStore {

    override suspend fun nowPlayingRest(page: Int): Result<List<MovieDomain>>? {
        return try {
            val response = ApiClient.build()?.getNowPlaying(BuildConfig.API_KEY, page)

            response?.let {
                if (it.isSuccessful && it.body() != null) {
                    Result.Success(MovieMapper.mapFromRestListEntity(it.body()!!.results))
                } else {
                    Result.Error(Exception(it.message()))
                }
            } ?: run {
                Result.Error(Exception("An error ocurred"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun popularRest(page: Int): Result<List<MovieDomain>>? {
        return try {
            val response = ApiClient.build()?.getPopular(BuildConfig.API_KEY, page)

            response?.let {
                if (it.isSuccessful && it.body() != null) {
                    Result.Success(MovieMapper.mapFromRestListEntity(it.body()!!.results))
                } else {
                    Result.Error(Exception(it.message()))
                }
            } ?: run {
                Result.Error(Exception("An error ocurred"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun topRatedRest(page: Int): Result<List<MovieDomain>>? {
        return try {
            val response = ApiClient.build()?.getTopRated(BuildConfig.API_KEY, page)

            response?.let {
                if (it.isSuccessful && it.body() != null) {
                    Result.Success(MovieMapper.mapFromRestListEntity(it.body()!!.results))
                } else {
                    Result.Error(Exception(it.message()))
                }
            } ?: run {
                Result.Error(Exception("An error ocurred"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun upcomingRest(page: Int): Result<List<MovieDomain>>? {
        return try {
            val response = ApiClient.build()?.getUpcoming(BuildConfig.API_KEY, page)

            response?.let {
                if (it.isSuccessful && it.body() != null) {
                    Result.Success(MovieMapper.mapFromRestListEntity(it.body()!!.results))
                } else {
                    Result.Error(Exception(it.message()))
                }
            } ?: run {
                Result.Error(Exception("An error ocurred"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
