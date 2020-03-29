package com.noblecilla.data.datasource.rest

import com.noblecilla.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    private var services: ApiServices? = null

    fun build(): ApiServices? {
        val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(BuildConfig.API_HOST)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()

        services = retrofit.create(ApiServices::class.java)

        return services as ApiServices
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ApiServices {

        @GET("movie/now_playing")
        suspend fun getNowPlaying(
            @Query("api_key") key: String,
            @Query("page") page: Int
        ): Response<MovieResponse>

        @GET("movie/popular")
        suspend fun getPopular(
            @Query("api_key") key: String,
            @Query("page") page: Int
        ): Response<MovieResponse>

        @GET("movie/top_rated")
        suspend fun getTopRated(
            @Query("api_key") key: String,
            @Query("page") page: Int
        ): Response<MovieResponse>

        @GET("movie/upcoming")
        suspend fun getUpcoming(
            @Query("api_key") key: String,
            @Query("page") page: Int
        ): Response<MovieResponse>
    }
}
