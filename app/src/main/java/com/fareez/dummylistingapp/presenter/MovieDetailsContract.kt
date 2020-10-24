package com.fareez.dummylistingapp.presenter

import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.presenter.base.BaseContract

class MovieDetailsContract {

    interface View: BaseContract.View{
        fun onSuccessDetails(data: MovieDetailsModel)
        fun onErrorDetails(msg: String)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun getMovieDetails(id: String, apiKey: String)
    }

}