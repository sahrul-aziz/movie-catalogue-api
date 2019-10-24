package com.dicoding.submission.movieapi.service

import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.model.TvShowBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface DataRetrieverService {

    @GET("movie")
    fun getMovie(@Query("api_key") apiKey: String,
                 @Query("language") language: Locale) : Call<MovieBase>

    @GET("tv")
    fun getTvShow(@Query("api_key") apiKey: String,
                  @Query("language") language: Locale) : Call<TvShowBase>

}