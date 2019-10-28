package com.dicoding.submission.movieapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieBase(
    val page : Int = 0,
    val total_results : Int = 0,
    val total_pages : Int = 0,
    val results : List<MovieResults> = listOf()
) : Parcelable