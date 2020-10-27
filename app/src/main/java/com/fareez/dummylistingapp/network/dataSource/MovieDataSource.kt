package com.fareez.dummylistingapp.network.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.fareez.dummylistingapp.model.Details
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.network.State
import com.fareez.dummylistingapp.network.api.ApiServiceInterface
import com.fareez.dummylistingapp.util.Constants
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MovieDataSource (
        private val networkService: ApiServiceInterface,
        private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Details>(){

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Details>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getMoviesByPage(Constants.MOVIE_TITLE, 1, Constants.API_KEY)
                        .subscribe(
                                { response ->
                                    updateState(State.DONE)
                                    if(response != null){
                                        callback.onResult(response.search,
                                            null,
                                            2
                                        )
                                    }
                                },
                                {
                                    updateState(State.ERROR)
                                    setRetry(Action { loadInitial(params, callback) })
                                }
                        )
        )
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Details>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getMoviesByPage(Constants.MOVIE_TITLE, params.key, Constants.API_KEY)
                        .subscribe(
                                { response ->
                                    updateState(State.DONE)
                                    if(response != null){
                                        callback.onResult(response.search,
                                            params.key + 1
                                        )
                                    }
                                },
                                {
                                    updateState(State.ERROR)
                                    setRetry(Action { loadAfter(params, callback) })
                                }
                        )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Details>) {
    }


    fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry(){
        if(retryCompletable != null){
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }



}