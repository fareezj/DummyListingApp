package com.fareez.dummylistingapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fareez.dummylistingapp.R
import com.fareez.dummylistingapp.di.component.DaggerActivityComponent
import com.fareez.dummylistingapp.di.module.ActivityModule
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.presenter.MovieContract
import com.fareez.dummylistingapp.util.Constants
import kotlinx.android.synthetic.main.activity_movie.*
import javax.inject.Inject


class MovieActivity : AppCompatActivity(), MovieContract.View {

    // Dependency "Consumer"
    @Inject
    lateinit var presenter: MovieContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        injectDependency()
        //Connect MovieActivity to the presenter
        presenter.attach(this)
        initView()
    }

    //Override method in MovieContract
    override fun onSuccess(data: MovieModel) {

//        val movieList: List<MovieModel.Details>? = data.search
//        //Initialize adapter to recyclerView
//        if(movieList != null){
//            val myAdapter = MyAdapter(movieList, this)
//            rv_movie_list.layoutManager = GridLayoutManager(
//                this, 2, GridLayoutManager.VERTICAL, false)
//            rv_movie_list.adapter = myAdapter
//
//        }

    }

    //Override method in MovieContract
    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Log.i("ERROR", msg)
    }

    //Initialize UI and execute getMovies function defined in Presenter
    private fun initView(){
        btn_search_movie.setOnClickListener {
            val inputTitle = et_search_movie.text.toString()
            presenter.getMovies(inputTitle, Constants.API_KEY)
        }
    }

    // Injecting dependency defined in DaggerActivityComponent
    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)

    }

}