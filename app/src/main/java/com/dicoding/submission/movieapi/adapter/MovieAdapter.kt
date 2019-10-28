package com.dicoding.submission.movieapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.MovieBase
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter(private var movieList: ArrayList<MovieBase>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun setData(items: ArrayList<MovieBase>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], position)
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: MovieBase, position: Int) {
            with(itemView) {
                movie_title.text = movieItem.results[position].title
                movie_overview.text = movieItem.results[position].overview
                movie_score.text = movieItem.results[position].vote_count.toString()
            }
        }
    }

}