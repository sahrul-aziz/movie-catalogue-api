package com.dicoding.submission.movieapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieBase(
    val page : Int,
    val total_results : Int,
    val total_pages : Int,
    val results : List<MovieResults>
) : Parcelable