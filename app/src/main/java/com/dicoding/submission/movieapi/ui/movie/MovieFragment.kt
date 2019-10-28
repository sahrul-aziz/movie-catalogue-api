package com.dicoding.submission.movieapi.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.adapter.MovieAdapter
import com.dicoding.submission.movieapi.model.MovieBase
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private val movieList = ArrayList<MovieBase>()
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel =
            ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        movieViewModel.listMovie.observe(this, Observer {
            movieAdapter.setData(it)
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_movie.layoutManager = LinearLayoutManager(activity)
        rv_movie.setHasFixedSize(true)
        val movieAdapter = MovieAdapter(movieList)
        rv_movie.adapter = movieAdapter
    }
}