package com.dicoding.submission.movieapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowBase(
    val page : Int = 0,
    val total_results : Int = 0,
    val total_pages : Int = 0,
    val results : List<TvShowResults> = listOf()
) : Parcelable