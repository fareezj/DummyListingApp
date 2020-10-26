package com.fareez.dummylistingapp.network.dataSource

import androidx.lifecycle.MutableLiveData
import com.fareez.dummylistingapp.model.Details
import com.fareez.dummylistingapp.network.api.ApiServiceInterface
import io.reactivex.disposables.CompositeDisposable
import androidx.paging.DataSource


class MovieDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val networkService: ApiServiceInterface)
    : DataSource.Factory<Int, Details>() {

    val moviesDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Details> {
        val movieDataSource = MovieDataSource(networkService, compositeDisposable)
        moviesDataSourceLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}