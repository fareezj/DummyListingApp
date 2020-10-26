package com.fareez.dummylistingapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
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

    // Dependency "Consumer"
    @Inject
    lateinit var presenter: MovieDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //Set actionBar back button
        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        injectDependency()
        //Connect MovieDetailsActivity to the presenter
        presenter.attachMovieDetail(this)
        initView()
    }

    //Initialize UI and execute getMoviesDetails function defined in Presenter
    private fun initView(){
        val getMovieID: String? = intent.getStringExtra("movieID")
        if(getMovieID != null){
            presenter.getMovieDetails(getMovieID, Constants.API_KEY)
        }
    }

    // Back button function to previous page
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Override method in MovieContract and inflate the results in respective UI
    override fun onSuccessDetails(data: MovieDetailsModel) {

        tv_title_details.text = data.title
        tv_year_details.text = data.year
        tv_rated_details.text = data.rated
        tv_released_details.text = data.released
        tv_director_details.text = data.director
        tv_actors_details.text = data.actors
        tv_language_details.text = data.language
        tv_imdbRating_details.text = data.imdbRating
        tv_production_details.text = data.production
        tv_plot_details.text = data.plot
        Picasso.get()
            .load(data.poster)
            .fit()
            .error(android.R.drawable.ic_menu_report_image)
            .into(iv_movie_detail)
    }

    override fun onErrorDetails(msg: String) {

    }

    // Injecting dependency defined in DaggerActivityComponent
    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)

    }
}