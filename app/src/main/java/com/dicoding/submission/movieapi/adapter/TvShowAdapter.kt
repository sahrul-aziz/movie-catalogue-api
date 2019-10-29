package com.dicoding.submission.movieapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.model.TvShowBase
import com.dicoding.submission.movieapi.model.TvShowResults
import com.dicoding.submission.movieapi.utils.AppConst.IMAGE_URL
import kotlinx.android.synthetic.main.tv_show_list_item.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var tvShow = TvShowBase()

    fun setData(items: TvShowBase) {
        tvShow = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tv_show_list_item, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShow.results.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShow.results[position])
    }


    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShowItem: TvShowResults) {
            with(itemView) {
                tv_show_title.text = tvShowItem.original_name
                tv_show_overview.text = tvShowItem.overview
                tv_show_score.text = tvShowItem.vote_average.toString()
                Glide.with(context).load("${IMAGE_URL}/w185${tvShowItem.poster_path}").into(tv_show_poster)
            }
        }
    }
}