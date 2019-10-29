package com.dicoding.submission.movieapi.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.service.MovieService
import com.dicoding.submission.movieapi.utils.AppConst.API_KEY
import com.dicoding.submission.movieapi.utils.AppConst.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MovieViewModel : ViewModel() {


    val listMovie = MutableLiveData<MovieBase>()

    internal fun retrieveMovie() {
        val okHttpClient = OkHttpClient().newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(MovieService::class.java)
        service.getMovie(API_KEY).enqueue(object : Callback<MovieBase> {
            override fun onFailure(call: Call<MovieBase>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(call: Call<MovieBase>, response: Response<MovieBase>) {
                if (response.code() == 200) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listMovie.postValue(responseBody)
                    }
                }
            }
        })

    }
}