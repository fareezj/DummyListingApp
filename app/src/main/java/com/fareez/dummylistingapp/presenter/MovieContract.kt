package com.fareez.dummylistingapp.presenter

import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.presenter.base.BaseContract

class MovieContract {

    interface View: BaseContract.View{
        fun onSuccess(data: MovieModel)
        fun onError(msg: String)

    }

    interface Presenter: BaseContract.Presenter<View>{
        fun getMovies(title: String, apiKey: String)
    }
}