package com.dicoding.submission.movieapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.MovieBase
import com.dicoding.submission.movieapi.model.MovieResults
import com.dicoding.submission.movieapi.ui.MovieDetailActivity
import com.dicoding.submission.movieapi.utils.AppConst.IMAGE_URL
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movie =  MovieBase()

    fun setData(items: MovieBase) {
        movie = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movie.results.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movie.results[position])
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: MovieResults) {
            with(itemView) {
                movie_title.text = movieItem.title
                var overview: String = resources.getString(R.string.overview_not_available)
                if (movieItem.overview != "") {
                    overview = movieItem.overview
                }
                movie_overview.text = overview
                val score = "Score: ${movieItem.vote_average}"
                movie_score.text = score
                Glide.with(context).load("${IMAGE_URL}/w185${movieItem.poster_path}").into(movie_poster)
                setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra("movie", movieItem)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}