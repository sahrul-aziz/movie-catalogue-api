package com.dicoding.submission.movieapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.MovieResults

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra<MovieResults>("movie")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (movie != null) {
            Toast.makeText(this, movie.overview, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
