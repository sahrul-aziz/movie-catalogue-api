package com.dicoding.submission.movieapi.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.model.TvShowBase
import com.dicoding.submission.movieapi.service.MovieService
import com.dicoding.submission.movieapi.utils.AppConst
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TvShowViewModel : ViewModel() {

    val listTvShow = MutableLiveData<TvShowBase>()

    internal fun retrieveTvShow() {
        val okHttpClient = OkHttpClient().newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(MovieService::class.java)
        service.getTvShow(AppConst.API_KEY).enqueue(object : Callback<TvShowBase> {
            override fun onFailure(call: Call<TvShowBase>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(call: Call<TvShowBase>, response: Response<TvShowBase>) {
                if (response.code() == 200) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listTvShow.postValue(responseBody)
                    }
                }
            }
        })

    }
}