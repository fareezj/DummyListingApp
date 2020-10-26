package com.fareez.dummylistingapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fareez.dummylistingapp.model.Details
import com.fareez.dummylistingapp.network.State
import com.fareez.dummylistingapp.network.api.ApiServiceInterface
import com.fareez.dummylistingapp.network.dataSource.MovieDataSource
import com.fareez.dummylistingapp.network.dataSource.MovieDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel: ViewModel() {

    private val networkService = ApiServiceInterface.getService()
    var movieList: LiveData<PagedList<Details>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val movieDataSourceFactory: MovieDataSourceFactory

    init {
        movieDataSourceFactory = MovieDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        movieList = LivePagedListBuilder(movieDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
            movieDataSourceFactory.moviesDataSourceLiveData,
            MovieDataSource::state
    )

    fun retry(){
        movieDataSourceFactory.moviesDataSourceLiveData.value?.retry()
    }

    fun refresh(){
        movieDataSourceFactory.moviesDataSourceLiveData.value?.invalidate()
    }

    fun listIsEmpty(): Boolean{
        return movieList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}