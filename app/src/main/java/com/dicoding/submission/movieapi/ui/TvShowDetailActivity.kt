package com.dicoding.submission.movieapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.TvShowResults
import com.dicoding.submission.movieapi.utils.AppConst
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import java.util.*

class TvShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        val tvShow = intent.getParcelableExtra<TvShowResults>("tvShow")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.title_tv_show_detail)

        if (tvShow != null) {
            Glide.with(this).load("${ AppConst.IMAGE_URL }/w185${tvShow.poster_path}").into(tv_show_detail_poster)
            tv_show_detail_title.text = tvShow.original_name
            var overview = tvShow.overview
            if (overview == "") overview = resources.getString(R.string.overview_not_available)
            tv_show_detail_overview.text = overview
            tv_show_detail_date.text = tvShow.first_air_date
            tv_show_detail_score.text = tvShow.vote_average.toString()
            tv_show_detail_popularity.text = tvShow.popularity.toString()
            tv_show_detail_language.text = getLanguage(tvShow.original_language)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getLanguage(language: String) : String {
        val locale = Locale(language)
        return locale.displayLanguage
    }
}
