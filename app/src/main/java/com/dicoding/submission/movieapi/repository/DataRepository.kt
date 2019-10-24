package com.dicoding.submission.movieapi.repository

import com.dicoding.submission.movieapi.service.DataRetrieverService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {

    private val BASE_URL: String = "https://api.themoviedb.org/3/discover/"
    private val API_KEY: String = "c03a13ce1ce7829b711cc35a1c64d960"


    fun create() : DataRetrieverService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(DataRetrieverService::class.java)
    }

}
