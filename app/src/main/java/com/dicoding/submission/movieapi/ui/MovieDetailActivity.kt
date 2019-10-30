package com.dicoding.submission.movieapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.MovieResults
import com.dicoding.submission.movieapi.utils.AppConst
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra<MovieResults>("movie")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (movie != null) {
            Glide.with(this).load("${ AppConst.IMAGE_URL }/w185${movie.poster_path}").into(movie_detail_poster)
            movie_detail_title.text = movie.title
            movie_detail_overview.text = movie.overview
            movie_detail_date.text = movie.release_date
            movie_detail_score.text = movie.vote_average.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
