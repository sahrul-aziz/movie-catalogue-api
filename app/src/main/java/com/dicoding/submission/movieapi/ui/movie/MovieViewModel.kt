package com.dicoding.submission.movieapi.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.movieapi.model.ErrorResponse
import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.service.MovieService
import com.dicoding.submission.movieapi.utils.AppConst.API_KEY
import com.dicoding.submission.movieapi.utils.AppConst.BASE_URL
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel : ViewModel() {

    val listMovie = MutableLiveData<MovieBase>()
    var errorResponse = MutableLiveData<ErrorResponse>()

    internal fun retrieveMovie() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        service.getMovie(API_KEY).enqueue(object : Callback<MovieBase> {
            override fun onFailure(call: Call<MovieBase>, t: Throwable) {
                listMovie.postValue(null)
                val error = ErrorResponse(
                    0,
                    "Something went wrong, please try again later!",
                    false
                )
                errorResponse.postValue(error)
            }

            override fun onResponse(call: Call<MovieBase>, response: Response<MovieBase>) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            listMovie.postValue(responseBody)
                        }
                    }
                } else {
                    listMovie.postValue(null)
                    val error = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                    errorResponse.postValue(error)
                }
            }
        })
    }
}