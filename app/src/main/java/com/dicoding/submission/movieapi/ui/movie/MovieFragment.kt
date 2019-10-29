package com.dicoding.submission.movieapi.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_movie, container, false)
        val movieAdapter = MovieAdapter()
        movieAdapter.notifyDataSetChanged()

        root.rv_movie.layoutManager = LinearLayoutManager(this.context)
        root.rv_movie.adapter = movieAdapter

        showLoading(true)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.listMovie.observe(this, Observer {
            if (it != null) {
                root.movie_empty.visibility = View.GONE
                movieAdapter.setData(it)
            } else {
                root.movie_empty.text = resources.getString(R.string.no_movie)
                root.movie_empty.visibility = View.VISIBLE
            }
            showLoading(false)
        })
        movieViewModel.retrieveMovie()
        return root
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            root.progressBarMovie.visibility = View.VISIBLE
        } else {
            root.progressBarMovie.visibility = View.GONE
        }
    }

}