package com.dicoding.submission.movieapi.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.service.MovieService
import com.dicoding.submission.movieapi.utils.AppConst.API_KEY
import com.dicoding.submission.movieapi.utils.AppConst.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieViewModel : ViewModel() {

    val listMovie = MutableLiveData<ArrayList<MovieBase>>()

    internal fun retrieveMovie() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val jsonCall = service.getMovie(API_KEY)

    }

    internal fun getMovie() : LiveData<ArrayList<MovieBase>> {
        return listMovie
    }
}