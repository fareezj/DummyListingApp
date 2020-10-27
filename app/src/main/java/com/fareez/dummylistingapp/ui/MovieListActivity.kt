package com.fareez.dummylistingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fareez.dummylistingapp.R
import com.fareez.dummylistingapp.adapter.MovieListAdapter
import com.fareez.dummylistingapp.network.State
import com.fareez.dummylistingapp.network.dataSource.MovieDataSource
import com.fareez.dummylistingapp.network.dataSource.MovieDataSourceFactory
import com.fareez.dummylistingapp.util.Constants
import com.fareez.dummylistingapp.viewModel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieDataSourceFactory: MovieDataSourceFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movieDataSourceInit: MovieDataSource? = null
        val movieDataSourceFactory: MovieDataSourceFactory? = null
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        initAdapter()
        initState()

        btn_search_movie.setOnClickListener {
            val movieInput = et_search_movie.text.toString()
            Constants.MOVIE_TITLE = movieInput
            recycler_view.recycledViewPool.clear();
            viewModel.refresh()
            initAdapter()
            initState()

        }
    }

    private fun initAdapter(){
        movieListAdapter = MovieListAdapter({ viewModel.retry() }, this)
        recycler_view.adapter = movieListAdapter
        viewModel.movieList.observe(this,
            Observer {
                movieListAdapter.submitList(it)
            })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                movieListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}