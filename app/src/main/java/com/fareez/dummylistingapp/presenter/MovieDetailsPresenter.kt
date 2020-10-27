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
    //Connecting to Model components (API Service)
    private val apiServices: ApiServiceInterface = ApiServiceInterface.getService()

    // Fetch data from API
    override fun getMovieDetails(id: String, apiKey: String) {

        //RxJava2 implementation for asynchronous process
        val subscribeMovieDetails = apiServices.getMovieDetails(id, apiKey)
                //Subscribe on background thread
            .subscribeOn(Schedulers.io())
                // Observe changes on main thread
            .observeOn(AndroidSchedulers.mainThread())
                // Observer will be notified if the data set changes
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
    }

    // Dispose Observer when it is not needed
    override fun unsubcribe() {
        subscriptions.clear()
    }

    override fun attach(view: MovieActivity) {
    }

    // Attach the presenter to its respective activity
    override fun attachMovieDetail(view: MovieDetailsActivity) {
        this.viewMovieDetails = view
    }
}