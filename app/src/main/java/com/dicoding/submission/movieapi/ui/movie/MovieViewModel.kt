package com.dicoding.submission.movieapi.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.movieapi.model.MovieBase

class MovieViewModel : ViewModel() {

    companion object {
        private const val API_KEY = "c03a13ce1ce7829b711cc35a1c64d960"
    }


    val listMovie = MutableLiveData<ArrayList<MovieBase>>()

    internal fun retrieveMovie() {

    }

    internal fun getMovie() : LiveData<ArrayList<MovieBase>> {
        return listMovie
    }
}