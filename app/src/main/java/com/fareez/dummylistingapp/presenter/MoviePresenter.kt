package com.fareez.dummylistingapp.presenter

import com.fareez.dummylistingapp.network.api.ApiServiceInterface
import com.fareez.dummylistingapp.ui.MovieActivity
import com.fareez.dummylistingapp.ui.MovieDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviePresenter: MovieContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MovieContract.View
    //Connecting to Model components (API Service)
    private val apiServices: ApiServiceInterface = ApiServiceInterface.create()

    // Fetch data from API
    override fun getMovies(title: String, apiKey: String) {
        val subscribe = apiServices.getMovies(title, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onSuccess(it)
            }, {
                error ->
                var msg = error.localizedMessage
                view.onError(msg)
            })
        subscriptions.add(subscribe)
    }


    override fun subscribe() {
    }

    // Dispose Observer when it is not needed
    override fun unsubcribe() {
        subscriptions.clear()
    }

    // Attach the presenter to its respective activity
    override fun attach(view: MovieActivity) {
        this.view = view
    }

    override fun attachMovieDetail(view: MovieDetailsActivity) {
    }

}