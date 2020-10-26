package com.fareez.dummylistingapp.presenter.base

import com.fareez.dummylistingapp.ui.MovieActivity
import com.fareez.dummylistingapp.ui.MovieDetailsActivity

//Base structure of Contract
class BaseContract {

    interface Presenter<in T>{
        fun subscribe()
        fun unsubcribe()
        fun attach(view: MovieActivity)
        fun attachMovieDetail(view: MovieDetailsActivity)
    }

    interface View{

    }
}