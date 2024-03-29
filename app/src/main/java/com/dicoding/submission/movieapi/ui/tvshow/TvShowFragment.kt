package com.dicoding.submission.movieapi.ui.tvshow

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
import com.dicoding.submission.movieapi.adapter.TvShowAdapter
import com.dicoding.submission.movieapi.model.ErrorResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_tv_show, container, false)
        tvShowAdapter = TvShowAdapter()
        tvShowAdapter.notifyDataSetChanged()

        root.rv_tv_show.layoutManager = LinearLayoutManager(this.context)
        root.rv_tv_show.adapter = tvShowAdapter

        showLoading(true)

        tvShowViewModel =
            ViewModelProviders.of(this, SavedStateVMFactory(this@TvShowFragment)).get(TvShowViewModel::class.java)
        tvShowViewModel.listTvShow.observe(this, Observer {
            if (it != null) {
                root.tv_show_empty.visibility = View.GONE
                tvShowAdapter.setData(it)
            } else {
                root.tv_show_empty.text = resources.getString(R.string.no_movie)
                root.tv_show_empty.visibility = View.VISIBLE
            }
            showLoading(false)
        })

        tvShowViewModel.errorResponse.observe(this, Observer {
            if (it != null) {
                showSnackbar(it)
                tvShowViewModel.errorResponse.value = null
            }
        })

        if (tvShowViewModel.getTvShow() == null) {
            showLoading(true)
            tvShowViewModel.retrieveTvShow()
        } else {
            val tvShowBase = tvShowViewModel.getTvShow()
            tvShowBase?.let { tvShowAdapter.setData(it) }
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tvShowViewModel.saveTvShow(tvShowViewModel.listTvShow.value)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            root.progressBarTvShow.visibility = View.VISIBLE
        } else {
            root.progressBarTvShow.visibility = View.GONE
        }
    }


    private fun showSnackbar(errorResponse: ErrorResponse){
        Snackbar.make(root, "Error [code: ${errorResponse.statusCode}]: ${errorResponse.statusMessage}", Snackbar.LENGTH_SHORT).show()
    }
}