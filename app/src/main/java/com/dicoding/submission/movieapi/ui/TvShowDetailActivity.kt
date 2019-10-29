package com.dicoding.submission.movieapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.TvShowResults

class TvShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        val tvShow = intent.getParcelableExtra<TvShowResults>("tvShow")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (tvShow != null) {
            Toast.makeText(this, tvShow.overview, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
