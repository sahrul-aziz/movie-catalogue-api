package com.dicoding.submission.movieapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.TvShowResults
import com.dicoding.submission.movieapi.utils.AppConst
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        val tvShow = intent.getParcelableExtra<TvShowResults>("tvShow")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (tvShow != null) {
            Glide.with(this).load("${ AppConst.IMAGE_URL }/w185${tvShow.poster_path}").into(tv_show_detail_poster)
            tv_show_detail_title.text = tvShow.original_name
            tv_show_detail_overview.text = tvShow.overview
            tv_show_detail_date.text = tvShow.first_air_date
            tv_show_detail_score.text = tvShow.vote_average.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
