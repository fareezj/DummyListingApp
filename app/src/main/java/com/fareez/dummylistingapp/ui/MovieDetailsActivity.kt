package com.fareez.dummylistingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fareez.dummylistingapp.R
import com.fareez.dummylistingapp.di.component.DaggerActivityComponent
import com.fareez.dummylistingapp.di.module.ActivityModule
import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.presenter.MovieDetailsContract
import com.fareez.dummylistingapp.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsContract.View {

    @Inject
    lateinit var presenter: MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        injectDependency()
        presenter.attachMovieDetail(this)
        initView()
    }

    private fun initView(){
        val getMovieID: String? = intent.getStringExtra("movieID")
        if(getMovieID != null){
            presenter.getMovieDetails(getMovieID, Constants.API_KEY)
        }
    }

    override fun onSuccessDetails(data: MovieDetailsModel) {

        tv_title_details.text = data.title
        tv_rated_details.text = data.rated
        tv_release_details.text = data.released
        tv_plot_details.text = data.plot
        Picasso.get()
            .load(data.poster)
            .fit()
            .error(android.R.drawable.ic_menu_report_image)
            .into(iv_movie_detail)
    }

    override fun onErrorDetails(msg: String) {
        TODO("Not yet implemented")
    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)

    }
}