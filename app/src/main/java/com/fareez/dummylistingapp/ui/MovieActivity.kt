package com.fareez.dummylistingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fareez.dummylistingapp.R
import com.fareez.dummylistingapp.adapter.MyAdapter
import com.fareez.dummylistingapp.di.component.DaggerActivityComponent
import com.fareez.dummylistingapp.di.module.ActivityModule
import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.presenter.MovieContract
import com.fareez.dummylistingapp.util.Constants
import kotlinx.android.synthetic.main.activity_movie.*
import javax.inject.Inject

class MovieActivity : AppCompatActivity(), MovieContract.View {

    @Inject
    lateinit var presenter: MovieContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        injectDependency()
        presenter.attach(this)
        initView()

    }

    override fun onSuccess(data: MovieModel) {

        val movieList: List<MovieModel.Details>? = data.search
        //Initialize adapter to recyclerView
        if(movieList != null){
            val myAdapter = MyAdapter(movieList, this)
            rv_movie_list.layoutManager = LinearLayoutManager(this)
            rv_movie_list.adapter = myAdapter
        }
        Log.i("FAREEZ", movieList?.get(1)?.Title.toString())

    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Log.i("FAREEZ", msg)
    }

    private fun initView(){
        btn_search_movie.setOnClickListener {
            val inputTitle = et_search_movie.text.toString()
            presenter.getMovies(inputTitle, Constants.API_KEY)
        }
    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)

    }

}