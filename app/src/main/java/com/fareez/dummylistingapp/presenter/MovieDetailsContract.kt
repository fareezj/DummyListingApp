package com.fareez.dummylistingapp.presenter

import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.presenter.base.BaseContract

class MovieDetailsContract {

    // Contract between Presenter and View components
    interface View: BaseContract.View{
        fun onSuccessDetails(data: MovieDetailsModel)
        fun onErrorDetails(msg: String)
    }

    // Contract between Presenter and Model components
    interface Presenter: BaseContract.Presenter<View>{
        fun getMovieDetails(id: String, apiKey: String)
    }

}