package com.dicoding.submission.movieapi.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.MovieBase
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = ArrayList<MovieBase>()

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
        holder.bind(movieList[position])
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: MovieBase) {
            with(itemView) {
                text_test.text = movieItem.total_pages.toString()
            }
        }
    }

}