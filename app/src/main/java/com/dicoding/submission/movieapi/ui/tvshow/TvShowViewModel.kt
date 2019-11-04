package com.dicoding.submission.movieapi.ui.tvshow

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.movieapi.model.ErrorResponse
import com.dicoding.submission.movieapi.model.TvShowBase
import com.dicoding.submission.movieapi.service.MovieService
import com.dicoding.submission.movieapi.utils.AppConst
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TvShowViewModel : ViewModel() {

    val listTvShow = MutableLiveData<TvShowBase>()
    var errorResponse = MutableLiveData<ErrorResponse>()

    internal fun retrieveTvShow() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var locale = Locale.getDefault().toString()
        locale = locale.replace("_", "-")

        val service = retrofit.create(MovieService::class.java)
        service.getTvShow(AppConst.API_KEY, locale).enqueue(object : Callback<TvShowBase> {
            override fun onFailure(call: Call<TvShowBase>, t: Throwable) {
                listTvShow.postValue(null)
                val error = ErrorResponse(
                    0,
                    "Something went wrong, please try again later!",
                    false
                )
                errorResponse.postValue(error)
            }

            override fun onResponse(call: Call<TvShowBase>, response: Response<TvShowBase>) {
                if (response.code() == 200) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listTvShow.postValue(responseBody)
                    }
                } else {
                    listTvShow.postValue(null)
                    val error = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                    errorResponse.postValue(error)
                }
            }
        })
    }
}