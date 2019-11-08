package com.dicoding.submission.movieapi.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.movieapi.R
import com.dicoding.submission.movieapi.adapter.MovieAdapter
import com.dicoding.submission.movieapi.model.ErrorResponse
import com.google.android.material.snackbar.Snackbar
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
        movieAdapter = MovieAdapter()
        movieAdapter.notifyDataSetChanged()

        root.rv_movie.layoutManager = LinearLayoutManager(this.context)
        root.rv_movie.adapter = movieAdapter

        movieViewModel = ViewModelProviders.of(this, SavedStateVMFactory(this@MovieFragment)).get(MovieViewModel::class.java)
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

        movieViewModel.errorResponse.observe(this, Observer {
            if (it != null) {
                showSnackbar(it)
                movieViewModel.errorResponse.value = null
            }
        })

        if (movieViewModel.getMovie() == null) {
            showLoading(true)
            movieViewModel.retrieveMovie()
        } else {
            val movieBase = movieViewModel.getMovie()
            movieBase?.let { movieAdapter.setData(it) }
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        movieViewModel.saveMovie(movieViewModel.listMovie.value)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            root.progressBarMovie.visibility = View.VISIBLE
        } else {
            root.progressBarMovie.visibility = View.GONE
        }
    }

    private fun showSnackbar(errorResponse: ErrorResponse){
        Snackbar.make(root, "Error [code: ${errorResponse.statusCode}]: ${errorResponse.statusMessage}", Snackbar.LENGTH_SHORT).show()
    }

}