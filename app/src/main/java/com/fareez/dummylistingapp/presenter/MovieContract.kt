package com.fareez.dummylistingapp.presenter

import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.presenter.base.BaseContract

class MovieContract {

    // Contract between Presenter and View components
    interface View: BaseContract.View{
        fun onSuccess(data: MovieModel)
        fun onError(msg: String)

    }

    // Contract between Presenter and Model components
    interface Presenter: BaseContract.Presenter<View>{
        fun getMovies(title: String, apiKey: String)
    }
}