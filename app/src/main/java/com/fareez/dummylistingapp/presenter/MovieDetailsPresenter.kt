package com.fareez.dummylistingapp.presenter

import com.fareez.dummylistingapp.network.api.ApiServiceInterface
import com.fareez.dummylistingapp.ui.MovieActivity
import com.fareez.dummylistingapp.ui.MovieDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter: MovieDetailsContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var viewMovieDetails: MovieDetailsContract.View
    private val apiServices: ApiServiceInterface = ApiServiceInterface.create()

    override fun getMovieDetails(id: String, apiKey: String) {
        val subscribeMovieDetails = apiServices.getMovieDetails(id, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewMovieDetails.onSuccessDetails(it)
            }, {
                    error ->
                var msg = error.localizedMessage
                viewMovieDetails.onErrorDetails(msg)
            })
        subscriptions.add(subscribeMovieDetails)
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unsubcribe() {
        subscriptions.clear()
    }

    override fun attach(view: MovieActivity) {
        TODO("Not yet implemented")
    }

    override fun attachMovieDetail(view: MovieDetailsActivity) {
        this.viewMovieDetails = view
    }
}