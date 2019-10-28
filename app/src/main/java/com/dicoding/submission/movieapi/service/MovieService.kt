package com.dicoding.submission.movieapi.service

import androidx.lifecycle.LiveData
import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.model.TvShowBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface MovieService {

    @GET("movie")
    fun getMovie(@Query("api_key") apiKey: String) : Call<MovieBase>

    @GET("tv")
    fun getTvShow(@Query("api_key") apiKey: String) : Call<TvShowBase>

}